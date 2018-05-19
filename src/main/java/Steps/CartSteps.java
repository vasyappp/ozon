package Steps;

import Pages.CartPage;
import io.qameta.allure.Step;
import org.junit.Assert;

/**
 * Описание шагов для страницы Корзины
 */
public class CartSteps {

    @Step("продукты присутствуют в Корзине")
    public void stepCheckCart() {
        new CartPage().checkProductNames();
    }

    @Step("Корзина очищена")
    public void stepClearCart() {
        new CartPage().removeAll();
    }

    @Step("Корзина пуста")
    public void stepIsCartEmpty() {
        Assert.assertTrue("Корзина не пуста", new CartPage().isCartEmpty());
    }
}
