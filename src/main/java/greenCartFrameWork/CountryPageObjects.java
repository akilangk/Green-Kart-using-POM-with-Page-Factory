package greenCartFrameWork;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CountryPageObjects {
    @FindBy(css = "div.wrapperTwo > div > select")
    public static WebElement countriesDropDown;
    @FindBy(css = "div.wrapperTwo > input")
    public static WebElement agreeCheckBox;
}
