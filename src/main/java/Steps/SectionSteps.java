package Steps;

import Pages.SectionPage;
import io.qameta.allure.Step;

/**
 * Описание шагов для страницы выбора типа продуктов
 */
public class SectionSteps {

    @Step("выбран вид продукта {0}")
    public void stepSelectProductType(String productTypeName) {
        new SectionPage().selectProductType(productTypeName);
    }
}
