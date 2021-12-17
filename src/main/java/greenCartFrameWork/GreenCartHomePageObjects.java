package greenCartFrameWork;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GreenCartHomePageObjects {
    @FindBy(css = ".search-keyword")
    public static WebElement searchTextBox;
    @FindBy(css = ".search-button")
    public static WebElement searchButton;
    @FindBy(css = ".product .product-name")
    public static WebElement veggieName;
    @FindBy(css = ".stepper-input>input")
    public static WebElement veggieCountTextBox;
    @FindBy(css = ".product-action>button")
    public static WebElement addToCartButton;
    @FindBy(css = ".cart-icon")
    public static WebElement cartIcon;
    @FindBy(css = ".cart-preview.active .action-block>button")
    public static WebElement proceedToCheckOutButton;
}
