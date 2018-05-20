package Pages;

import Utils.Cart;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Описание страницы Корзины сайта
 */
public class CartPage extends BasePage {

    @FindBy(xpath = ".//div[contains(@class, 'RemoveAll')]//..")
    WebElement removeAllButton; // Кнопка "Удалить все"

    @FindBy(xpath = ".//div[@class = 'eRemovedCartItems_removeAll jsRemoveAll']")
    WebElement removingInfoCloseButton; // Кнопка закрытия информации об удаленных товарах

    @FindBy(xpath = ".//div[contains(@class, 'bPageTitle')]/span")
    WebElement title; // Заголовок страницы


    /**
     * Метод получает список всех названий товаров в корзине
     *
     * @return Список названий продуктов
     */
    private List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@class = 'eCartControls_infoDate']"))));

        List<WebElement> products = driver.findElements(By.xpath
                (".//div[@class = 'eCartSplitItems']//div[contains(@class, 'bCartItem')]"));

        for (WebElement product : products) {
            String productName = product.findElement(By.xpath
                    (".//span[@class = 'eCartItem_nameValue']")).getText();
            productNames.add(productName);
        }

        return productNames;
    }

    /**
     * Метод проверяет, соответствует ли содержимое Корзины тому, что добавлялось во время выполнения теста
     *
     */
    public void checkProductNames() {
        List<String> actualProductNames = getAllProductNames();
        List<String> expectedProductNames = Cart.getInstance().getProductNames();

        for (String expectedName : expectedProductNames) {
            if (!actualProductNames.contains(expectedName))
                Assert.fail("Продукт \"" + expectedName + "\" не был добавлен в корзину");
        }

        for (String actualName : actualProductNames) {
            if (!expectedProductNames.contains(actualName))
                Assert.fail("В корзине лишний продукт: \"" + actualName + "\"");
        }
    }

    /**
     * Метод удаляет все товары из корзины
     *
     */
    public void removeAll() {
        String removeAllButtonXpath = ".//div[@class = 'eCartControls_buttons']";
        String removingInfoCloseButtonXpath = ".//div[@class = 'eRemovedCartItems_removeAll jsRemoveAll']";

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        while (true) {
            try {
                driver.findElement(By.xpath(removeAllButtonXpath));
            } catch (NoSuchElementException e) {
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                return;
            }

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedCondition<Boolean>) driver -> {
                try {
                    WebElement removeAllButton = driver.findElement(By.xpath(removeAllButtonXpath));
                    removeAllButton.click();
                    try {
                        driver.findElement(By.xpath(removingInfoCloseButtonXpath));
                        return true;
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                } catch (WebDriverException e) {
                    return false;
                }
            });

            wait.until((ExpectedCondition<Boolean>) driver -> {
                WebElement removingInfoCloseButton = driver.findElement(By.xpath(removingInfoCloseButtonXpath));
                waitVisibility(removingInfoCloseButton);
                try {
                    removingInfoCloseButton.click();
                    return true;
                } catch (WebDriverException e) {
                    return false;
                }
            });
        }
    }

    /**
     * Метод проверяет, пуста ли корзина
     *
     * @return Пуста ли корзина
     */
    public Boolean isCartEmpty() {
        return title.getText().contains("пуста");
    }
}
