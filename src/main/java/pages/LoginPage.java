package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 POM of LogIn Page
 Inputs: userName, password
 Buttons: logIn
 Created by Trifonova, 01.2022
 */
public class LoginPage {
    protected WebDriver driver;

    @FindBy(id="user-name")
    private WebElement userNameInput;

    @FindBy(css="[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(name="login-button")
    private WebElement loginBtn;

    public LoginPage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(this.driver, this);
    }

    public InventoryPage login (String userName, String password){
        String loginURL=driver.getCurrentUrl();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();
        String actualURL = driver.getCurrentUrl();
        if (actualURL.equals(loginURL))
            return null;
        else
            return new InventoryPage(driver);

    }

}

