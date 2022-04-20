package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/*
 POM of Inventory Page
 inventory.html
 Created by Trifonova, 01.2022
 */
public class InventoryPage {
    private static final String ADD_TO_CARD_LOCATOR = "//button[@id='add-to-cart-%s']";
    private static final String REMOVE_FROM_CARD_LOCATOR = "//button[@id='remove-%s']";
    protected WebDriver driver;
    @FindBy ( className = "shopping_cart_link" )
    private WebElement shoppingCartLink;

    @FindBy ( id = "reset_sidebar_link" )
    private WebElement resetAppLink;

    @FindBy ( id = "react-burger-menu-btn" )
    private WebElement burgerMenuBtn;

    @FindBy ( id = "react-burger-cross-btn" )
    private WebElement closeBurgerMenuBtn;

    @FindBy ( xpath = "//img[@class='inventory_item_img']" )
    private final List<WebElement> inventoryItemImgList = new ArrayList<WebElement>();

    @FindBy (xpath = "//button[contains(@class, 'btn_inventory')]")
    private final List<WebElement> inventoryItemBtnList = new ArrayList<WebElement>();


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public void addToCartByProductName(String productName) {
        String xPathOfProduct = String.format(ADD_TO_CARD_LOCATOR, productName);
        WebElement addToCartBtn = new WebDriverWait(driver, Duration.ofSeconds(10)).until(presenceOfElementLocated(xpath(xPathOfProduct)));
        if (addToCartBtn.isDisplayed())
            addToCartBtn.click();

    }

    public void removeFromCartByProductName(String productName) {
        String xPathOfProduct = String.format(REMOVE_FROM_CARD_LOCATOR, productName);
        WebElement removeBtn = new WebDriverWait(driver, Duration.ofSeconds(10)).until(presenceOfElementLocated(xpath(xPathOfProduct)));
        if (removeBtn.isDisplayed())
            removeBtn.click();
    }

    public boolean checkRemovedFromCartByProductName(String productName) {
        String xPathOfProduct = String.format(ADD_TO_CARD_LOCATOR, productName);
        WebElement addToCartBtn = new WebDriverWait(driver, Duration.ofSeconds(10)).until(presenceOfElementLocated(xpath(xPathOfProduct)));
        return addToCartBtn.isDisplayed();
    }

    public boolean checkAddedToCartByProductName(String productName) {
        String xPathOfProduct = String.format(REMOVE_FROM_CARD_LOCATOR, productName);
        WebElement removeBtn = new WebDriverWait(driver, Duration.ofSeconds(10)).until(presenceOfElementLocated(xpath(xPathOfProduct)));
        return removeBtn.isDisplayed();
    }


    public void resetApp() {
        burgerMenuBtn.click();
        resetAppLink.click();
        driver.navigate().refresh();
    }

    public CartPage goToCart() {
        shoppingCartLink.click();
        return new CartPage(driver);
    }

    public void addFirstFoundItemToCart() {
        List<WebElement> addToCartBtnList = driver.findElements(xpath("//*[contains(@id, 'add-to-cart-')]"));
        if (!addToCartBtnList.isEmpty())
            addToCartBtnList.get(0).click();
    }


    public List<WebElement> getInventoryItemImgList() {
        return inventoryItemImgList;
    }

    public List<WebElement> getInventoryItemBtnList() {
        return inventoryItemBtnList;
    }
}
