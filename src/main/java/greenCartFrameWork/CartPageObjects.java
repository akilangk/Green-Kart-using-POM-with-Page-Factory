package greenCartFrameWork;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPageObjects {
    @FindBy(css = ".products tbody > tr > td:nth-child(2)")
    public static List<WebElement> productNames;
    @FindBy(css = ".products > div > button")
    public static WebElement placeOrder;
}
