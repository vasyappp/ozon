package Steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ScenarioSteps {
    MenuSteps menuSteps = new MenuSteps();
    SectionSteps sectionSteps = new SectionSteps();
    ProductParametersSteps productParametersSteps = new ProductParametersSteps();
    FilteredProductsSteps filteredProductsSteps = new FilteredProductsSteps();
    ProductSteps productSteps = new ProductSteps();
    CartSteps cartSteps = new CartSteps();

    @When("^выбран раздел меню \"(.+)\"$")
    public void selectMenuOption(String optionName) {
        menuSteps.stepSelectMenuOption(optionName);
    }

    @When("^выбран вид продукта \"(.+)\"$")
    public void selectProductType(String productTypeName) {
        sectionSteps.stepSelectProductType(productTypeName);
    }

    @When("^выбраны производители \"(.+)\"$")
    public void selectBrands(String brands) {
        String[] splittedBrands = brands.split(", ");

        for (String brand : splittedBrands) {
            productParametersSteps.stepSelectBrand(brand);
        }
    }

    @When("^выставлена начальная цена \"(.+)\"$")
    public void setStartingPrice(String startingPrice) {
        productParametersSteps.stepSetStartingPrice(startingPrice);
    }

    @When("^выбраны \"(.+)\" товары$")
    public void addProductsToCart(String typeOfIndexes) {
        int amount = filteredProductsSteps.stepGetAmountOfProducts();
        int startingIndex = 0;

        if (typeOfIndexes.equalsIgnoreCase("четные")) {
            startingIndex = 2;
        } else if (typeOfIndexes.equalsIgnoreCase("нечетные")) {
            startingIndex = 1;
        } else
            Assert.fail("Неправильный оператор выбора товаров \"" +
                    typeOfIndexes + "\"");

        for (int i = startingIndex; i <= amount; i = i + 2) {
            filteredProductsSteps.stepGoToProduct(i);
            productSteps.stepAddToCart();
            BaseSteps.goToPreviousPage();
        }
    }

    @When("^выполнен переход в Корзину$")
    public void goToCart() {
        menuSteps.stepGoToCart();
    }

    @Then("^продукты присутствуют в Корзине$")
    public void checkCart() {
        cartSteps.stepCheckCart();
    }

    @When("^Корзина очищена$")
    public void clearCart() {
        cartSteps.stepClearCart();
    }

    @Then("^Корзина пуста$")
    public void isCartEmpty() {
        cartSteps.stepIsCartEmpty();
    }
}
