package Steps;

import Pages.ProductParametersPage;
import io.qameta.allure.Step;

/**
 * Описание щагов для раздела выставления параметров товаров
 */
public class ProductParametersSteps {

    @Step("выбран производитель {0}")
    public void stepSelectBrand(String brandName) {
        new ProductParametersPage().selectBrand(brandName);
    }

    @Step("выставлена стартовая цена {0}")
    public void stepSetStartingPrice(String startingPrice) {
        new ProductParametersPage().setStartingPrice(startingPrice);
    }
}
