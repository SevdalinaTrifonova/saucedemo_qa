package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    protected WebDriver driver;

    @FindBy(id="checkout")
    private WebElement checkoutBtn;


    public CartPage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(this.driver, this);
    }

    public CheckOutStepOnePage checkOutProceed(){
        checkoutBtn.click();
        return new CheckOutStepOnePage(driver);
    }
}
