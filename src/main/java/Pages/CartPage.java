package Pages;

import Utils.Cart;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание страницы Корзины сайта
 */
public class CartPage extends BasePage {

    @FindBy(xpath = ".//div[@class = 'eCartSplitItems']//div[contains(@class, 'bCartItem')]")
    List<WebElement> products; // Список продуктов в корзине

    @FindBy(xpath = ".//div[contains(@class, 'RemoveAll')]")
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
        scrollToElement(removeAllButton);
        removeAllButton.click();

        waitVisibility(removingInfoCloseButton);
        try {
            removingInfoCloseButton.click();
        } catch (WebDriverException e){
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", removingInfoCloseButton);
        }

        Cart.getInstance().clearCart();
    }

    /**
     * Метод проверяет, пуста ли корзина
     *
     * @return Пуста ли корзина
     */
    public boolean isCartEmpty() {
        return title.getText().contains("пуста");
    }
}
