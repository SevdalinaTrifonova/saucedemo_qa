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
 Images on Inventory page
 reads users from csv
 Created by Trifonova, 01.2022
 */
public class ItemImageLoadTest extends TestUtil {

    @Test (  dataProvider = "usersCsv", dataProviderClass = Users.class )
    public void imageLoadTest(String userName, String password, String expectedResult) {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login(userName, password);
        softAssert.assertEquals((!(inventoryPage == null)), Boolean.parseBoolean(expectedResult), String.format("Unexpected Login behaviour %s %s", userName, password));
        if (!(inventoryPage == null)) {
            List<WebElement> inventoryItemImgList = inventoryPage.getInventoryItemImgList();
            boolean successLoad = true;
            for (WebElement el :
                    inventoryItemImgList) {
                if (el.getAttribute("src").equals(applicationUrl + picture404Cfg)) {
                    successLoad = false;
                    break;
                }
            }
            softAssert.assertTrue(successLoad, String.format("Item images are not property loaded for %s %s", userName, password));
        }
        softAssert.assertAll();

    }

    @AfterMethod
    public void resetLoginPage() {
        driver.get(applicationUrl);
    }

}
