package com.saucedemo.selenium;

import base.TestUtil;
import dataproviders.ItemsToCart;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

/*
 Add or Remove Items to Cart
 reads test cases from itemToCart.json
 The test are executed as standard user
 Created by Trifonova, 01.2022
 */
public class ItemsToCartTest extends TestUtil {
    protected InventoryPage inventoryPage;

    @BeforeTest
    public void logIn() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(userNameCfg, passwordCfg);
        Assert.assertFalse((inventoryPage == null), "Wrong credentials");
    }

    @BeforeMethod
    public void resetApp() {
        inventoryPage.resetApp();
    }


    @Test ( dataProvider = "itemsToCartJson", dataProviderClass = ItemsToCart.class )
    public void itemToCart(Map<String, Object> actionWithCart) {
        List<Object> itemsList = (List<Object>) actionWithCart.get("items");
        SoftAssert softAssert = new SoftAssert();
        for (Object o : itemsList) {
            Map<String, Object> item;
            item = (Map<String, Object>) o;
            String itemName = (String) item.get("itemName");
            String action = (String) item.get("action");
            if (action.equals("remove")) {
                inventoryPage.removeFromCartByProductName(itemName);
                softAssert.assertTrue(inventoryPage.checkRemovedFromCartByProductName(itemName), String.format("%s not removed", itemName));
            } else {
                inventoryPage.addToCartByProductName(itemName);
                softAssert.assertTrue(inventoryPage.checkAddedToCartByProductName(itemName), String.format("%s not added", itemName));
            }

        }
        softAssert.assertAll();
    }

}

