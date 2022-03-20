package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 POM of Cart Page
 cart.html
 Created by Trifonova, 01.2022
 */
public class CheckOutStepTwoPage {

    protected WebDriver driver;

    @FindBy (id="finish")
    private WebElement finishBtn;

    public CheckOutStepTwoPage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(this.driver, this);
    }

    public CheckOutCompletePage finishCheckoutBtn(){
        String currentURL = driver.getCurrentUrl();
        finishBtn.click();
        if (currentURL.equals(driver.getCurrentUrl()))
            return null;
        else
            return new CheckOutCompletePage(driver);
    }

}
