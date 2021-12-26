package avicpagefactory.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTests extends BaseTest {
    private static final String CURRENCY = " грн";
    private static final int LENGTH_OF_CURRENCY_TEXT = CURRENCY.length();

    @Test(priority = 1)
    public void cartCounterAfterRemove() {
        getHomePage().clickOnSamsungButton();
        getSamsungPage().clickOnFirstBuyProductButton();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow()); // waiting for modal window
        getCartPage().clickOnContinueShoppingButton();
        getCartPage().implicitWait(3); // wait for unload the cart
        getSamsungPage().clickOnCartIcon();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow()); // waiting for modal window
        getCartPage().clickOnRemoveProductFromCartButton();
        getCartPage().implicitWait(10); // wait for reload the cart
        getCartPage().clickOnContinueShoppingButton();
        getCartPage().implicitWait(10); // wait for unload the cart
        assertEquals(getSamsungPage().cartIcon.getText(), "0"); // check that cart-counter shows "0"
    }

    @Test(priority = 2)
    public void CheckEqualSum() {
        getHomePage().clickOnSamsungButton();
        getSamsungPage().waitForPageLoadComplete(30);
        getSamsungPage().clickOnFirstBuyProductButton();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow());
        getCartPage().clickOnContinueShoppingButton();
        getSamsungPage().clickOnSecondBuyProductButton();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow());
        getCartPage().clickOnFirstProductIncrementButton();
        getCartPage().clickOnSecondProductIncrementButton();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow());
        getCartPage().clickOnCloseCartButton();
        getCartPage().implicitWait(3); // wait for unload the cart
        getSamsungPage().clickOnCartIcon();
        getCartPage().waitVisibilityOfElement(30, getCartPage().showCartWindow());
        assertEquals(getCartPage().getCalculatedSumInCart(LENGTH_OF_CURRENCY_TEXT), getCartPage().getTotalSumInCart(LENGTH_OF_CURRENCY_TEXT));

    }

}
