package Pages;

import Steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Описание меню-бара сайта Ozon
 */
public class MenuPage extends BasePage {
    @FindBy(xpath = ".//div[@class = 'bHeaderCategoryLinks ']/a")
    List<WebElement> menuOptions; // Список разделов сайта

    @FindBy(xpath = ".//div[contains(@class, 'mCart')]/a")
    WebElement cartButton; // Кнопка перехода в Корзину

    /**
     * Метод выбирает раздел сайта
     *
     * @param optionName Название раздела
     */
    public void selectMenuOption(String optionName) {
        selectCollectionItem(optionName, menuOptions);
    }

    /**
     * Метод перехода в Корзину
     */
    public void goToCart() {
        scrollToElement(cartButton);
        click(cartButton);
    }
}
