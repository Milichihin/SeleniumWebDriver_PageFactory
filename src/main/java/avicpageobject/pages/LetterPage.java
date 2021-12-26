package avicpageobject.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class LetterPage extends BasePage {

    private static final String DEPARTMENT_MARKER = "//a[text()='Оптовый отдел']";
    private static final String NAME_FIELD = "(//input[@placeholder='Ваше имя'])[4]"; //[4]
    private static final String MESSAGE_FIELD = "(//textarea[@placeholder='Текст сообщения'])[3]"; //[3]
    private static final String EMAIL_FIELD = "(//input[@placeholder='Электронная почта'])[3]"; //[3]
    private static final String SUBMIT_BUTTON = "(//button[@type='submit'])[6]"; //[6]
    private static final String ERROR_EMAIL_NOTATION = "//div[@data-error='Некорректный email']";

    public LetterPage(WebDriver driver) {
        super(driver);
    }

    public void chooseDepartment() {
        driver.findElement(xpath(DEPARTMENT_MARKER)).click();
    }

    public void fillTheNameField(final String nameText) {
        driver.findElement(xpath(NAME_FIELD)).sendKeys(Keys.chord(Keys.CONTROL, "a"), nameText);
    }

    public void fillTheMessageField(final String messageText) {
        driver.findElement(xpath(MESSAGE_FIELD)).sendKeys(Keys.chord(Keys.CONTROL, "a"), messageText);
    }

    public void fillTheMailField(String mailtext) {
        driver.findElement(xpath(EMAIL_FIELD)).sendKeys(Keys.chord(Keys.CONTROL, "a"), mailtext);
    }

    public void clickOnSubmitButton() {
        driver.findElement(xpath(SUBMIT_BUTTON)).click();
    }

    public String showErrorNotation() {
        return ERROR_EMAIL_NOTATION;
    }

}
