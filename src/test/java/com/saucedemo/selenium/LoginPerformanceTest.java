package com.saucedemo.selenium;

import base.TestUtil;
import dataproviders.Users;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;

/*
 Log in Performance Test,
 reads test cases from loginData.csv
 Created by Trifonova, 01.2022
 */
public class LoginPerformanceTest extends TestUtil {
    protected InventoryPage inventoryPage;
    protected Long stdResponseTime;

    @BeforeTest
    public void measureStandardUserPerformance() {
        LoginPage loginPage = new LoginPage(driver);
        Long startTime = System.currentTimeMillis();
        inventoryPage = loginPage.login(userNameCfg, passwordCfg);
        Long endTime = System.currentTimeMillis();
        stdResponseTime = endTime - startTime;
        Assert.assertFalse((inventoryPage == null), "Wrong credentials");
        //set back to Login page
        driver.get(applicationUrl);

    }

    @Test ( dataProvider = "usersCsv", dataProviderClass = Users.class )
    public void loginPerformanceTest(String userName, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        long startTime = System.currentTimeMillis();
        InventoryPage inventoryPage = loginPage.login(userName, password);
        long  endTime = System.currentTimeMillis();
        long  responseTime = endTime - startTime;
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals((!(inventoryPage == null)), Boolean.parseBoolean(expectedResult), String.format("Unexpected Login behaviour %s %s", userName, password));
        softAssert.assertTrue(responseTime < stdResponseTime+1000, String.format("Response for user %s is too slow", userName));
        softAssert.assertAll();
    }

    @AfterMethod
    public void resetLoginPage() {
        driver.get(applicationUrl);
    }
}
