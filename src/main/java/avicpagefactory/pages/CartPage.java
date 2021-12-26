package avicpagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[contains (text(), 'Продолжить покупки')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//i[@class='icon icon-close js-btn-close']")
    private WebElement removeProductFromCartButton;

    @FindBy(xpath = "//span[@class='js_plus btn-count btn-count--plus ']")
    private List<WebElement> getIncrementButtons;

    @FindBy(xpath = "//button[@class='fancybox-button fancybox-close-small']")
    private WebElement closeCartButton;

    @FindBy(xpath = "//div[@class='total-h']/span[@class='prise']")
    private List<WebElement> listOfPricesElements;

    @FindBy(xpath = "//div[@class='item-total']/span[@class='prise']")
    private WebElement totalCost;

    @FindBy(xpath = "//div[@id='js_cart']")
    private WebElement cartWindow;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement showCartWindow() {
        return cartWindow;
    }

    public void clickOnFirstProductIncrementButton() {
        getIncrementButtons.get(0).click();
    }

    public void clickOnSecondProductIncrementButton() {
        getIncrementButtons.get(1).click();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickOnRemoveProductFromCartButton() {
        removeProductFromCartButton.click();
    }

    public void clickOnCloseCartButton() {
        closeCartButton.click();
    }


    public int getCalculatedSumInCart(int lengthOfCurrency) {

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

        String totalSumText = totalCost.getText(); // collect data of total cost
        StringBuffer bufferString = new StringBuffer(totalSumText); // create a buffer string to cut "_грн"
        String amountOfCharactersToCut = bufferString.substring(0, totalSumText.length() - lengthOfCurrency); // how many characters should we cut
        int totalSumInteger = Integer.parseInt(amountOfCharactersToCut.toString()); // cut and convert string to integer

        return totalSumInteger;
    }

}
