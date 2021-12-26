package avicpagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaptopPage extends BasePage {

    @FindBy(xpath = "//div[@class='filter__items checkbox']")
    private List<WebElement> getFilterList;

    public LaptopPage(WebDriver driver) {
        super(driver);
    }

    public int amountOfLaptopFilters() {
        return getFilterList.size();
    }


}
