package greenCartTestCases;

import commonFunctions.CommonFunctions;
import greenCartFrameWork.CartPageObjects;
import greenCartFrameWork.GreenCartHomePageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddingVeggiesToCart extends CommonFunctions {
    public void checkTheVeggieName(WebElement element, String veggieName){
        String actualText = element.getText().replace(" - 1 Kg","");
        Assert.assertEquals(actualText,veggieName);
    }
    @Test
    public void checkSearchingAndAddingVeggiesToCart() {
        PageFactory.initElements(driver, GreenCartHomePageObjects.class);
        for (int veggieIndex = 0; veggieIndex < numberOfVeggies(); veggieIndex++) {
            GreenCartHomePageObjects.searchTextBox.clear();
            GreenCartHomePageObjects.searchTextBox.sendKeys(veggies.get(veggieIndex));
            GreenCartHomePageObjects.searchButton.click();
            checkTheVeggieName(GreenCartHomePageObjects.veggieName,veggies.get(veggieIndex));
            GreenCartHomePageObjects.veggieCountTextBox.clear();
            GreenCartHomePageObjects.veggieCountTextBox.sendKeys(quantity.get(veggieIndex));
            GreenCartHomePageObjects.addToCartButton.click();
        }
        GreenCartHomePageObjects.cartIcon.click();
        GreenCartHomePageObjects.proceedToCheckOutButton.click();
    }

    @Test
    public void checkTheAddedVeggiesCountInTheCart(){
        PageFactory.initElements(driver, CartPageObjects.class);
        Assert.assertEquals(CartPageObjects.productNames.size(),numberOfVeggies());
        CartPageObjects.placeOrder.click();
    }
}
