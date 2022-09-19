package persistence;

import model.Category;
import model.Purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPurchase(String name, double cost, Purchase purchase) {
        assertEquals(name, purchase.getName());
        assertEquals(cost, purchase.getCost());
    }

    protected void checkCategory(String name, int percent, Category category) {
        assertEquals(name, category.getName());
        assertEquals(percent, category.getPercent());
    }
}