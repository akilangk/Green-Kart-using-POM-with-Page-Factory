package greenCartTestCases;

import commonFunctions.CommonFunctions;
import greenCartFrameWork.CartPageObjects;
import greenCartFrameWork.GreenCartHomePageObjects;
import greenCartFrameWork.OrderPlacementPageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestGreenCart extends CommonFunctions {
    public void checkTheVeggieName(WebElement element, String vegetableName) {
        Assert.assertEquals(element.getText().replace(" - 1 Kg", ""), vegetableName);
    }

    @Test
    public void checkSearchingAndAddingVeggiesToCart() throws InterruptedException {
        PageFactory.initElements(driver, GreenCartHomePageObjects.class);
        for (int veggieIndex = 0; veggieIndex < numberOfVeggies(); veggieIndex++) {
            GreenCartHomePageObjects.searchTextBox.clear();
            GreenCartHomePageObjects.searchTextBox.sendKeys(veggies.get(veggieIndex));
            Thread.sleep(1000);
            GreenCartHomePageObjects.searchButton.click();
            checkTheVeggieName(GreenCartHomePageObjects.veggieName, veggies.get(veggieIndex));
            GreenCartHomePageObjects.veggieCountTextBox.clear();
            GreenCartHomePageObjects.veggieCountTextBox.sendKeys(quantity.get(veggieIndex));
            GreenCartHomePageObjects.addToCartButton.click();
        }
        GreenCartHomePageObjects.cartIcon.click();
        GreenCartHomePageObjects.proceedToCheckOutButton.click();
    }

    @Test
    public void checkTheAddedVeggiesCountInTheCart() {
        PageFactory.initElements(driver, CartPageObjects.class);
        Assert.assertEquals(CartPageObjects.productNames.size(), numberOfVeggies());
        CartPageObjects.placeOrder.click();
    }

    @Test
    public void checkOrderPlacing() {
        PageFactory.initElements(driver, OrderPlacementPageObjects.class);
        Select countryDropDown = new Select(OrderPlacementPageObjects.countriesDropDown);
        countryDropDown.selectByVisibleText(country.get(0));
        OrderPlacementPageObjects.agreeCheckBox.click();
        OrderPlacementPageObjects.proceedButton.click();
        Assert.assertTrue(OrderPlacementPageObjects.text.isDisplayed());
    }
}
