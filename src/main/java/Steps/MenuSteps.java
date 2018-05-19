package Steps;

import Pages.MenuPage;
import io.qameta.allure.Step;

/**
 * Описание шагов для меню-бара сайта Ozon
 */
public class MenuSteps {

    @Step("выбран раздел меню {0}")
    public void stepSelectMenuOption(String optionName) {
        new MenuPage().selectMenuOption(optionName);
    }

    @Step("выполнен переход в Корзину")
    public void stepGoToCart() {
        new MenuPage().goToCart();
    }
}
