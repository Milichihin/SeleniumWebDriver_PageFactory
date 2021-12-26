package avicpagefactory.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LetterPage extends BasePage {

    @FindBy(xpath = "//a[text()='Оптовый отдел']")
    private WebElement departmentMarker;

    @FindBy(xpath = "(//input[@placeholder='Ваше имя'])[4]")
    private WebElement nameField;

    @FindBy(xpath = "(//textarea[@placeholder='Текст сообщения'])[3]")
    private WebElement messageField;

    @FindBy(xpath = "(//input[@placeholder='Электронная почта'])[3]")
    private WebElement emailField;

    @FindBy(xpath = "(//button[@type='submit'])[6]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@data-error='Некорректный email']")
    private WebElement errorNotation;

    public LetterPage(WebDriver driver) {
        super(driver);
    }

    public WebElement showErrorNotation() {
        return errorNotation;
    }

    public void chooseDepartment() {
        departmentMarker.click();
    }

    public void fillTheNameField(final String nameText) {
        nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), nameText);
    }

    public void fillTheMessageField(final String messageText) {
        messageField.sendKeys(Keys.chord(Keys.CONTROL, "a"), messageText);
    }

    public void fillTheMailField(String mailtext) {
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), mailtext);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }


}
