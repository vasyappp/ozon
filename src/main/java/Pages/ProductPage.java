package Pages;

import Utils.Cart;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = ".//div[@class = 'eSaleBlock_centerMessage']")
    WebElement noProductMessage;


    /**
     * Метод добавляет товар в Корзину
     *
     */
    public void addToCart() {
        if (isElementPresent(noProductMessage))
            return;

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
