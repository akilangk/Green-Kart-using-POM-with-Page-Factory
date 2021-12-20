package greenCartFrameWork;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPlacementPageObjects {
    @FindBy(css = ".wrapperTwo > div > select")
    public static WebElement countriesDropDown;
    @FindBy(css = "div.wrapperTwo > input")
    public static WebElement agreeCheckBox;
    @FindBy(css = "div.wrapperTwo > button")
    public static WebElement proceedButton;
    @FindBy(css = "div.wrapperTwo > span:nth-child(1)")
    public static WebElement text;
}
