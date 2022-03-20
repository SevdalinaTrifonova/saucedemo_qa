package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckOutCompletePage {
    protected WebDriver driver;

    public CheckOutCompletePage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(this.driver, this);
    }
}
