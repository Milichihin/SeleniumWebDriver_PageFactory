package avicpagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='https://avic.ua/noutbuki'][@class='category-box-item']")
    private WebElement laptopCatalogButton;

    @FindBy(xpath = "//a[text()='Письмо директору']")
    private WebElement letterToDirector;

    @FindBy(xpath = "//a[@href='/brand-samsung']")
    private WebElement samsungPage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnLaptopButton() {
        laptopCatalogButton.click();
    }

    public void clickOnLetterButton() {
        letterToDirector.click();
    }

    public void clickOnSamsungButton() {
        samsungPage.click();
    }


}
