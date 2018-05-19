package Steps;

import Utils.TestProperties;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Описание базовых шагов
 */
public class BaseSteps {
    protected static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public static void setUp() {
        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        baseUrl = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @After
    public static void tearDown() {
        driver.quit();
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void goToPreviousPage() {
        driver.navigate().back();
    }

    public static void closePopup() {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement popUpCloseButton = driver.findElement(By.xpath
                    (".//div[contains(@class, 'jsCloseBanner')]"));

            if ((popUpCloseButton != null) && (popUpCloseButton.isDisplayed()))
                popUpCloseButton.click();
        } catch (Exception e) {
            System.out.println("No popup here");
        } finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }
}
