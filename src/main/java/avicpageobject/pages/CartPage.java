package avicpageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class CartPage extends BasePage {

    public static final String CONTINUE_SHOPPING_BUTTON = "//a[contains (text(), 'Продолжить покупки')]";
    public static final String REMOVE_PRODUCT_FROM_CART_BUTTON = "//i[@class='icon icon-close js-btn-close']";
    public static final String INCREMENT_PRODUCT_BUTTONS = "//span[@class='js_plus btn-count btn-count--plus ']";
    public static final String CLOSE_CART_BUTTON = "//button[@class='fancybox-button fancybox-close-small']";
    public static final String PRODUCT_PRICES = "//div[@class='total-h']/span[@class='prise']";
    public static final String TOTAL_COST = "//div[@class='item-total']/span[@class='prise']";
    public static final String CART_WINDOW = "//div[@id='js_cart']";


    public CartPage(WebDriver driver) {
        super(driver);
    }

    List<WebElement> getIncrementButtons() {
        return driver.findElements(xpath(INCREMENT_PRODUCT_BUTTONS));
    }

    public String showCartWindow() {
        return CART_WINDOW;
    }

    public void clickOnFirstProductIncrementButton() {
        getIncrementButtons().get(0).click();
    }

    public void clickOnSecondProductIncrementButton() {
        getIncrementButtons().get(1).click();
    }

    public void clickOnContinueShoppingButton() {
        driver.findElement(xpath(CONTINUE_SHOPPING_BUTTON)).click();
    }

    public void clickOnRemoveProductFromCartButton() {
        driver.findElement(xpath(REMOVE_PRODUCT_FROM_CART_BUTTON)).click();
    }

    public void clickOnCloseCartButton() {
        driver.findElement(xpath(CLOSE_CART_BUTTON)).click();
    }


    public int getCalculatedSumInCart(int lengthOfCurrency) {

        List<WebElement> listOfPricesElements = driver.findElements(xpath(PRODUCT_PRICES)); // collect priceList
        ArrayList<Integer> arrayOfPricesIntegers = new ArrayList<>(); // create empty list for prices

        for (WebElement pricePerProduct : listOfPricesElements
        ) {
            int amountOfCharactersToCut = pricePerProduct.getAttribute("textContent").length() - lengthOfCurrency; // how many characters should we cut
            StringBuffer bufferString = new StringBuffer(pricePerProduct.getAttribute("textContent")); // collect value of each element from listOfPrices
            String cutCurrencyText = bufferString.substring(0, amountOfCharactersToCut); // cut "_грн"
            arrayOfPricesIntegers.add(Integer.parseInt(cutCurrencyText.toString())); // convert to integer and add to List
        }

        List<WebElement> listOfProductsNumberElements = driver.findElements(xpath("//input[@class='js-changeAmount count']")); // collect list of products number
        ArrayList<Integer> arrayOfProductsNumberIntegers = new ArrayList<>(); // create empty list for counts

        for (WebElement element : listOfProductsNumberElements
        ) {
            arrayOfProductsNumberIntegers.add(Integer.parseInt(element.getAttribute("value"))); // convert to integer and add to List
        }

        ArrayList<Integer> numberPricesMultiply = new ArrayList<>(); // create empty list for multipliers

        for (int i = 0; i < arrayOfPricesIntegers.size(); i++) {
            numberPricesMultiply.add(arrayOfPricesIntegers.get(i) * arrayOfProductsNumberIntegers.get(i)); //multiply the price by the quantity
        }

        int calculatedSum = 0; // variable for sum of costs

        for (int multiplication : numberPricesMultiply
        ) {
            calculatedSum += multiplication; //sum of costs
        }

        return calculatedSum;
    }

    public int getTotalSumInCart(int lengthOfCurrency) {

        String totalSumText = driver.findElement(xpath(TOTAL_COST)).getText(); // collect data of total cost
        StringBuffer bufferString = new StringBuffer(totalSumText); // create a buffer string to cut "_грн"
        String amountOfCharactersToCut = bufferString.substring(0, totalSumText.length() - lengthOfCurrency); // how many characters should we cut
        int totalSumInteger = Integer.parseInt(amountOfCharactersToCut.toString()); // cut and convert string to integer

        return totalSumInteger;
    }

}
