package com.saucedemo.selenium;

import base.TestUtil;
import dataproviders.Clients;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

/*
 Validate clients data and checks out p
 Test cases are stored in clientData.csv
 The test is executed as standard user
 Created by    Trifonova, 01.2022
 */

public class CheckOutTest extends TestUtil {
    protected CheckOutStepOnePage checkOutStepOnePage;
    protected CheckOutStepTwoPage checkOutStepTwoPage;
    protected CheckOutCompletePage checkOutCompletePage;
    private String checkOutStepOnePageURL;

    @BeforeTest
    public void goToCheckOut() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login(userNameCfg, passwordCfg);
        Assert.assertFalse((inventoryPage == null), "Wrong credentials");
        // @afterTest
        inventoryPage.resetApp();
        inventoryPage.addFirstFoundItemToCart();
        CartPage cartPage = inventoryPage.goToCart();
        checkOutStepOnePage = cartPage.checkOutProceed();
        checkOutStepOnePageURL = driver.getCurrentUrl();
    }


    @Test (  dataProvider = "clientDataCsv", dataProviderClass = Clients.class )
    public void proceedCheckout(String firstName, String lastName, String postalCode, String expectedResult, String expectedErrorMessage) {
        SoftAssert softAssert = new SoftAssert();
        checkOutStepTwoPage = checkOutStepOnePage.fillInClientsData(firstName, lastName, postalCode);
        softAssert.assertEquals(!(checkOutStepTwoPage == null), Boolean.parseBoolean(expectedResult),
                String.format("Unexpected Client data fill in behaviour %s %s %s", firstName, lastName, postalCode));
        if (checkOutStepTwoPage == null) {
            String errorMessage = checkOutStepOnePage.getErrorMessage();
            softAssert.assertEquals(errorMessage, expectedErrorMessage, String.format("Unexpected error message: %s", errorMessage));
        } else {
            //Continue Checkout
            checkOutCompletePage = checkOutStepTwoPage.finishCheckoutBtn();
            softAssert.assertFalse(checkOutCompletePage == null, "Unsuccessful Finish of Check out");
        }
        softAssert.assertAll();
    }

    @AfterMethod
    //go back to checkOutStepOnePage
    public void resetCheckOutStepOnePage() {
        driver.get(checkOutStepOnePageURL);
    }
}
