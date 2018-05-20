package Steps;

import Pages.CartPage;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebDriverWait wait = new WebDriverWait(BaseSteps.getDriver(), 10);

        try {
            wait.until((ExpectedCondition<Boolean>) driver -> new CartPage().isCartEmpty());
        } catch (TimeoutException e) {
            Assert.fail("Корзина не пуста");
        }
    }
}
