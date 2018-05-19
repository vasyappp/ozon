package Pages;

import Steps.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Описание базовой страницы и методов, общих для всех страниц
 */
public class BasePage {
    WebDriver driver = BaseSteps.getDriver();

    public BasePage() {
        PageFactory.initElements(driver, this);
        BaseSteps.closePopup();
    }

    /**
     * Метод прокручивает страницу к заданному элементу
     *
     * @param element Элемент, к которому требуется прокрутить страницу
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /**
     * Метод заполняет поле заданным значением
     *
     * @param field Поле для заполнения
     * @param value Значение, которое требуется ввести
     */
    public void fillField(WebElement field, String value) {
        scrollToElement(field);
        field.clear();
        field.sendKeys(value);
        field.sendKeys(Keys.ENTER);
    }

    /**
     * Метод выбирает из коллекции элемент, содержащий заданный текст
     *
     * @param itemName Заданный текст
     * @param collection Коллекция элементов
     */
    public void selectCollectionItem(String itemName, List<WebElement> collection) {
        for (WebElement item : collection) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                scrollToElement(item);
                item.click();
                return;
            }
        }
        Assert.fail("Не найден элемент коллекции - " + itemName);
    }

    /**
     * Метод ожидания отображения элемента на странице
     *
     * @param element Искомый элемент
     * @param time Время ожидания отображения
     */
    public void waitVisibility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Метод ожидания отображения элемента на странице
     *
     * @param element Искомый элемент
     */
    public void waitVisibility(WebElement element) {
        waitVisibility(element, 10);
    }

    public boolean isElementPresent(WebElement element) {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    /**
     * Метод ожидает возможности кликнуть на элемент
     *
     * @param element Искомый элемент
     */
    public void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        Wait<WebDriver> wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
