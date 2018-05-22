package Pages;

import Utils.Cart;
import org.junit.Assert;
import org.openqa.selenium.*;
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

    @FindBy(xpath = ".//div[@class = 'eCartSplitItems']//div[contains(@class, 'bCartItem')]")
    List<WebElement> products;

    @FindBy(xpath = ".//div[@class = 'eCartControls_infoDate']")
    WebElement date;


    /**
     * Метод получает список всех названий товаров в корзине
     *
     * @return Список названий продуктов
     */
    private List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(date));

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        while (true) {
            if (!isElementPresent(removeAllButton))
                return;

            removeAllClick();

            closeInfoClick();
        }
    }

    /**
     * Метод нажимает кнопку Корзины "Удалить все"
     */
    private void removeAllClick() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                removeAllButton.click();
                if (isElementPresent(removingInfoCloseButton))
                    return true;
                else
                    return false;
            } catch (WebDriverException e) {
                return false;
            }
        });
    }

    /**
     * Метод закрывает окно с информацией об удаленных товарах
     */
    private void closeInfoClick() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                removingInfoCloseButton.click();
                return true;
            } catch (WebDriverException e) {
                return false;
            }
        });
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
