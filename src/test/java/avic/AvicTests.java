package avic;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicTests {


    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void howManyFiltersHasLaptop() throws InterruptedException {
        driver.findElement(xpath("//a[@href='https://avic.ua/noutbuki'][@class='category-box-item']")).click(); // click to laptop category
        List<WebElement> filterList = driver.findElements(xpath("//div[@class='filter__items checkbox']")); // get a list of laptop filters
        assertEquals(filterList.size(), 16); // compare an actual number of laptop filters with the requirement (16)
    }


    @Test(priority = 2)
    public void checkMailForm() throws InterruptedException {

        String name = "any"; // entered data for name field
        String message = "any"; // entered data for message field
        String [] arrOfMail = {"1any", "2any@", "3any@1", "4any@3any.2any"}; // array of entered data for mail field

        driver.findElement(xpath("//a[text()='Письмо директору']")).click();
        driver.findElement(xpath("//a[text()='Оптовый отдел']")).click();
        WebElement nameField = driver.findElement(xpath("(//input[@placeholder='Ваше имя'])[4]")); // previously create access to nameField
        WebElement messageField = driver.findElement(xpath("(//textarea[@placeholder='Текст сообщения'])[3]")); // previously create access to messageField
        WebElement mailField = driver.findElement(xpath("(//input[@placeholder='Электронная почта'])[3]")); // previously create access to mailField
        WebElement submit = driver.findElement(xpath("(//button[@type='submit'])[6]")); // previously create access to submit button
        System.out.println("submit: " + submit); // just checking in Console
        boolean isValid = false; // variable to check error

        nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), name); // select previous text and paste the new one to the nameField
        messageField.sendKeys(Keys.chord(Keys.CONTROL, "a"), message); // select previous text and paste the new one to the messageField

        // iterating over the array of emails to check validation method
        for ( String email: arrOfMail) {
            mailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), email); // select previous text and paste the new one to the mailField
            submit.click(); // click submit button
            WebDriverWait waitForErr = new WebDriverWait(driver, 3000); // create a waiter for error notation
            waitForErr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-error='Некорректный email']"))); // waiting for error notation

            WebElement errField = driver.findElement(xpath("//div[@data-error='Некорректный email']")); // create access to error notation

            // checking the availability of error notation
            if (errField != null) {
                isValid = false; // error notation was created
            } else {
                isValid = true; // error notation was not created
            }
        }

        assertEquals(isValid, false);
    }


    @Test(priority = 3)
    public void cartCounterAfterRemove() {

        driver.findElement(xpath("//a[@href='/brand-samsung']")).click(); // go to samsung page
        driver.findElement(xpath("(//a[@class='prod-cart__buy'])[1]")).click(); // add to cart any first product

        WebDriverWait waitForModal = new WebDriverWait(driver, 10); // create a waiter
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        driver.findElement(xpath("//a[contains (text(), 'Продолжить покупки')]")).click(); // click to continue

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // wait for unload the cart

        driver.findElement(xpath("(//div[@class='active-cart-item js_cart_count'])[2]")).click(); // go to the cart

        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        driver.findElement(xpath("//i[@class='icon icon-close js-btn-close']")).click(); // remove product from the cart

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // waiting for reload
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        driver.findElement(xpath("//button[@class='fancybox-button fancybox-close-small']")).click(); // close cart window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // waiting for reload
        //waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        assertEquals(driver.findElement(xpath("(//div[@class='active-cart-item js_cart_count'])[2]")).getText(), "0"); // check that cart-counter shows "0"

    }

    @Test(priority = 4)
    public void CheckEqualSum() {

        driver.findElement(xpath("//a[@href='/brand-samsung']")).click(); // go to samsung page
        WebDriverWait waitForDisplay = new WebDriverWait(driver, 10); // create a waiter
        waitForDisplay.until(ExpectedConditions.visibilityOf( driver.findElement(xpath("(//a[@class='prod-cart__buy'])[1]")))); // waiting for display at least first product

        driver.findElement(xpath("(//a[@class='prod-cart__buy'])[1]")).click(); // add to cart any first product

        WebDriverWait waitForModal = new WebDriverWait(driver, 10); // create a waiter
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window


        driver.findElement(xpath("//a[contains (text(), 'Продолжить покупки')]")).click(); // click to continue
        driver.findElement(xpath("(//a[@class='prod-cart__buy'])[2]")).click(); // add to cart any second product

        //WebDriverWait waitForModal3 = new WebDriverWait(driver, 10); // create a waiter
        //waitForModal3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        driver.findElement(xpath("(//span[@class='js_plus btn-count btn-count--plus '])[1]")).click(); // increment product1
        driver.findElement(xpath("(//span[@class='js_plus btn-count btn-count--plus '])[2]")).click(); // increment product2
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        driver.findElement(xpath("//button[@class='fancybox-button fancybox-close-small']")).click(); // close cart window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait for unload the cart

        driver.findElement(xpath("(//div[@class='active-cart-item js_cart_count'])[2]")).click(); // go to the cart
        waitForModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        List<WebElement> priceList = driver.findElements(xpath("//div[@class='total-h']/span[@class='prise']")); // collect priceList
        ArrayList<Integer> arrPrice = new ArrayList<>(); // create empty list for prices
        for (WebElement element: priceList
             ) {
            int cutUah = element.getAttribute("textContent").length()-4; // how many characters should we cut
            StringBuffer strBuffer = new StringBuffer(element.getAttribute("textContent")); // collect value of each element from priceList
            String newStr = strBuffer.substring(0, cutUah); // cut "_грн"
            arrPrice.add(Integer.parseInt(newStr.toString())); // convert to integer and add to List
        }

        List<WebElement> countList = driver.findElements(xpath("//input[@class='js-changeAmount count']")); // collect list of products amount
        ArrayList<Integer> arrCount = new ArrayList<>(); // create empty list for counts
        for (WebElement element: countList
             ) {
            arrCount.add(Integer.parseInt(element.getAttribute("value"))); // convert to integer and add to List
        }

        ArrayList<Integer> multiply = new ArrayList<>(); // create empty list for multipliers

        for (int i = 0; i<arrPrice.size(); i++) {
            multiply.add(arrPrice.get(i) * arrCount.get(i)); //multiply the price by the quantity
        }

        int calculated = 0; // variable for sum of costs

        for (int el: multiply
             ) {
            calculated += el; //sum of costs
        }

        String preTotalStr = driver.findElement(xpath("//div[@class='item-total']/span[@class='prise']")).getText(); // collect data of total cost
        StringBuffer bufferStr = new StringBuffer(preTotalStr); // create a buffer string to cut "_грн"
        String totalStr = bufferStr.substring(0, preTotalStr.length()-4); // how many characters should we cut
        int total = Integer.parseInt(totalStr.toString()); // cut and convert string to integer

        //WebDriverWait waitForModal5 = new WebDriverWait(driver, 10); // create a waiter
        //waitForModal5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js_cart']"))); // waiting for modal window

        assertEquals(calculated, total); // check that total == calculated

    }

    @AfterMethod
    public void tearDown() {
        driver.close(); // close the driver
    }
}
