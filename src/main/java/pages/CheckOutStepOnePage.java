package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOnePage {

    protected WebDriver driver;

    @FindBy(className="shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy (id="first-name")
    private WebElement firstNameInput;

    @FindBy (id="last-name")
    private WebElement lastNameInput;

    @FindBy (id="postal-code")
    private WebElement postCodeInput;

    @FindBy (id="continue")
    private WebElement continueBtn;

    public CheckOutStepOnePage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(this.driver, this);
    }
    public CheckOutStepTwoPage fillInClientsData(String firstName, String lastName, String postalCode){
        String currentURL = driver.getCurrentUrl();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        postCodeInput.clear();
        postCodeInput.sendKeys(postalCode);

        continueBtn.click();
        if (currentURL.equals(driver.getCurrentUrl()))
            return null;
        else
            return new CheckOutStepTwoPage(driver);
    }

    public String getErrorMessage(){
        WebElement errorMessageDiv;
        errorMessageDiv = driver.findElement(By.cssSelector("[data-test='error']"));
        return errorMessageDiv.getText();
    }

}
