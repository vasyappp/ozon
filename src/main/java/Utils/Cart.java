package Utils;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart ourInstance = new Cart();
    private List<String> productNames;

    public static Cart getInstance() {
        return ourInstance;
    }

    private Cart() {
        productNames = new ArrayList<>();
    }

    public void putProductName(String productName) {
        productNames.add(productName);
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public boolean isProductInList(String productName) {
        if (productNames.contains(productName))
            return true;
        else
            return false;
    }

    public void clearCart() {
        productNames = new ArrayList<>();
    }
}
