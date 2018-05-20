package Pages;

import Utils.Cart;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Описание страницы информации о товаре
 */
public class ProductPage extends BasePage {
    @FindBy(xpath = ".//div[contains(text(), 'Добавить в корзину')]")
    WebElement addToCartButton; // Кнопка добавления товара в Корзину

    @FindBy(xpath = ".//h1[@class = 'bItemName']")
    WebElement productName; // Название товара

    @FindBy(xpath = ".//span[@class = 'eOzonStatus_scoreText']")
    WebElement ozonScore; // Количество баллов для начисления


    /**
     * Метод добавляет товар в Корзину
     *
     */
    public void addToCart() {
        try {
            addToCartButton.click();
        } catch (WebDriverException e) {
            waitVisibility(ozonScore);
            scrollToElement(ozonScore);
            click(addToCartButton);
        }

        Cart.getInstance().putProductName(productName.getText());
    }
}
