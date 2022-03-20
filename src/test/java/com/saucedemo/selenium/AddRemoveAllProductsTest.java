package com.saucedemo.selenium;

import base.TestUtil;
import dataproviders.Users;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

/*
 Add Remove all Items on Inventory page
 reads users from csv
 Created by Trifonova, 01.2022
 */
public class AddRemoveAllProductsTest extends TestUtil {

    @Test ( dataProvider = "usersCsv", dataProviderClass = Users.class )
    public void addRemoveAllProducts(String userName, String password, String expectedResult) {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login(userName, password);
        softAssert.assertEquals((!(inventoryPage == null)), Boolean.parseBoolean(expectedResult), String.format("Unexpected Login behaviour %s %s", userName, password));
        if (!(inventoryPage == null)) {
            inventoryPage.resetApp();
            List<WebElement> inventoryItemBtnList = inventoryPage.getInventoryItemBtnList();
            for (WebElement el :
                    inventoryItemBtnList) {
                String itemName = el.getAttribute("name").substring(12);
                inventoryPage.addToCartByProductName(itemName);
                softAssert.assertTrue(inventoryPage.checkAddedToCartByProductName(itemName), String.format("%s not added for user %s", itemName, userName));
            }
            for (WebElement el :
                    inventoryItemBtnList) {
                String itemName = el.getAttribute("name").substring(7);
                inventoryPage.removeFromCartByProductName(itemName);
                softAssert.assertTrue(inventoryPage.checkRemovedFromCartByProductName(itemName), String.format("%s not removed for user %s", itemName, userName));
            }

        }
        softAssert.assertAll();

    }

    @AfterMethod
    public void resetLoginPage() {
        driver.get(applicationUrl);
    }

}

