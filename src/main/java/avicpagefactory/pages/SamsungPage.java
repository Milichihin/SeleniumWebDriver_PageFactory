package avicpagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SamsungPage extends BasePage {

    @FindBy(xpath = "//a[@class='prod-cart__buy']")
    private List<WebElement> getBuyProductButtons;

    @FindBy(xpath = "(//div[@class='active-cart-item js_cart_count'])[2]")
    public WebElement cartIcon;


    public SamsungPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnFirstBuyProductButton() {
        getBuyProductButtons.get(0).click();
    }

    public void clickOnSecondBuyProductButton() {
        getBuyProductButtons.get(1).click();
    }

    public void clickOnCartIcon() {
        cartIcon.click();
    }


}
