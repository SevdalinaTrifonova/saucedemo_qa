package com.saucedemo.selenium;

import base.TestUtil;
import dataproviders.Users;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

/*
 Log in Test, Successful and unsuccessful
 reads test cases from loginData.csv
 Created by Trifonova, 01.2022
 */
public class LoginTest extends TestUtil {
    protected InventoryPage inventoryPage;

    @Test(dataProvider = "usersCsv", dataProviderClass = Users.class)
    public void loginTest(String userName, String password, String expectedResult){
        LoginPage loginPage=new LoginPage(driver);
        inventoryPage=loginPage.login(userName,password);
        Assert.assertEquals(!(inventoryPage==null),Boolean.parseBoolean(expectedResult),String.format("Unexpected Login behaviour %s %s", userName,password));
    }
    @AfterMethod
    public void reloadLoginPage(){
        driver.get(applicationUrl);
    }

}

