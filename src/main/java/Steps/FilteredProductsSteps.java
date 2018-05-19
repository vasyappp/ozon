package Steps;

import Pages.FilteredProductsPage;
import io.qameta.allure.Step;

/**
 * Описание шагов для страницы товаров с заданными параметрами
 */
public class FilteredProductsSteps {
    public int stepGetAmountOfProducts() {
        return new FilteredProductsPage().getProductsAmount();
    }

    @Step("выполнен переход к товару №{0}")
    public void stepGoToProduct(int index) {
        FilteredProductsPage filteredProductsPage = new FilteredProductsPage();

        filteredProductsPage.goToProduct(filteredProductsPage.selectProductByIndex(index));
    }
}
