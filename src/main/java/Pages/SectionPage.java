package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Метод описывает страницу выбора типа товара
 */
public class SectionPage extends BasePage {
    @FindBy(xpath = ".//a[contains(@class, 'eLeftMainMenu')]")
    List<WebElement> productTypes; // Список типов продуктов


    /**
     * Метод выбирает заданный тип продукта из списка
     *
     * @param productTypeName Тип продукта
     */
    public void selectProductType(String productTypeName) {
        selectCollectionItem(productTypeName, productTypes);
    }
}
