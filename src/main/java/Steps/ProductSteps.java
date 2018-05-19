package Steps;

import Pages.ProductPage;
import io.qameta.allure.Step;

/**
 * Описание шагов для страницы информации о продукте
 */
public class ProductSteps {

    @Step("продукт добавлен в Корзину")
    public void stepAddToCart() {
        new ProductPage().addToCart();
    }
}
