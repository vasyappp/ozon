package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Описание страницы, содержащей отобранные товары с заданными параметрами
 */
public class FilteredProductsPage extends BasePage {
    @FindBy(xpath = ".//div[@id = 'bTilesModeShow']/div")
    List<WebElement> products; // Список всех товаров на странице


    /**
     * Метод перехода на страницу информации о выбранном товаре
     *
     * @param product Выбранный товар
     */
    public void goToProduct(WebElement product) {
        WebElement productName = product.findElement(By.xpath
                (".//div[@itemprop = 'name']"));
        scrollToElement(productName);
        click(productName);
    }

    /**
     * Метод выбирает из списка всех товаров товар с заданным индексом
     *
     * @param index Номер товара в списке
     *
     * @return Товар
     */
    public WebElement selectProductByIndex(int index) {
        if (index > products.size()) {
            Assert.fail("Невозможно получить продукт под номером " + index +
                    ": на странице меньшее количество продуктов");
            return null;
        } else {
            return products.get(index - 1);
        }
    }

    /**
     * Метод возвращает количество товаров на странице
     *
     * @return Количество товаров на странице
     */
    public int getProductsAmount() {
        return products.size();
    }
}
