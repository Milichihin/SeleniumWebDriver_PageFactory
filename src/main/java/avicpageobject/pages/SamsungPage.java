package avicpageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class SamsungPage extends BasePage {

    public static final String BUY_PRODUCT_BUTTONS = "//a[@class='prod-cart__buy']"; //[1]
    public static final String CART_ICON = "(//div[@class='active-cart-item js_cart_count'])[2]";


    public SamsungPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getBuyProductButtons() {
        return driver.findElements(xpath(BUY_PRODUCT_BUTTONS));
    }

    public void clickOnFirstBuyProductButton() {
        getBuyProductButtons().get(0).click();
    }

    public void clickOnSecondBuyProductButton() {
        getBuyProductButtons().get(1).click();
    }

    public void clickOnCartIcon() {
        driver.findElement(xpath(CART_ICON)).click();
    }

    public WebElement cartIcon() {
        return driver.findElement(xpath(CART_ICON));
    }

}
