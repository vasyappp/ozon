package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Описание раздела выбора параметров товара
 *
 */
public class ProductParametersPage extends BasePage {
    @FindBy(xpath = ".//div[@id = 'facetControl_brand']//*[@class = 'eFilterList_OptionLink']")
    List<WebElement> brands; // Список производителей

    @FindBy(xpath = ".//input[@class = 'eFromToInput_InputField mFrom']")
    WebElement priceInputFrom; // Поле ввода начальной цены

    @FindBy(xpath = ".//div[@id = 'price_filter']//div[@class = 'bFilterApply']/div")
    WebElement applyButton; // Кнопка "Применить" для цены


    /**
     * Метод выбирает заданного производителя
     *
     * @param brandName Производитель
     */
    public void selectBrand(String brandName) {
        for (WebElement brandOption : brands) {
            if (brandOption.getText().contains(brandName)) {
                scrollToElement(brandOption);
                brandOption.click();

                return;
            }
        }

        Assert.fail("Не найден производитель под названием \"" + brandName + "\"");
    }

    /**
     * Метод выставляет начальную цену товара
     *
     * @param startingPrice Начальная цена
     */
    public void setStartingPrice(String startingPrice) {
        fillField(priceInputFrom, startingPrice);
        applyButton.click();
    }
}
