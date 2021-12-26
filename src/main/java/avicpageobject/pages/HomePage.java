package avicpageobject.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class HomePage extends BasePage {

    private static final String LAPTOP_CATALOG_BUTTON = "//a[@href='https://avic.ua/noutbuki'][@class='category-box-item']";
    private static final String LETTER_TO_DIRECTOR = "//a[text()='Письмо директору']";
    private static final String SAMSUNG_PAGE = "//a[@href='/brand-samsung']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnLaptopButton() {
        driver.findElement(xpath(LAPTOP_CATALOG_BUTTON)).click();
    }

    public void clickOnLetterButton() {
        driver.findElement(xpath(LETTER_TO_DIRECTOR)).click();
    }

    public void clickOnSamsungButton() {
        driver.findElement(xpath(SAMSUNG_PAGE)).click();
    }


}
