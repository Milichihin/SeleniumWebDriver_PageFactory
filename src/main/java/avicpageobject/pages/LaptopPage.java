package avicpageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class LaptopPage extends BasePage {

    public static final String SEARCH_RESULTS_LAPTOP_FILTER_LIST = "//div[@class='filter__items checkbox']";

    public LaptopPage(WebDriver driver) {
        super(driver);
    }

    public int amountOfLaptopFilters() {
        return getFilterList().size();
    }

    List<WebElement> getFilterList() {
        return driver.findElements(xpath(SEARCH_RESULTS_LAPTOP_FILTER_LIST));
    }

}
