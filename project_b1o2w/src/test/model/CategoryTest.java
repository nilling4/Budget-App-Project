package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category foodCategory;
    private List<Purchase> purchases;
    private Purchase hotDog;
    private Purchase burger;
    private User noel;

    @BeforeEach
    public void setUp() {
        foodCategory = new Category("food category", 25);
        hotDog = new Purchase("Hot dog", 5);
        burger = new Purchase("Burger", 6);
        noel = new User(100, "Noel");
    }

    @Test
    public void testCategory() {
        assertEquals(0, foodCategory.getListPurchases().size());
        assertEquals("food category", foodCategory.getName());
        assertEquals(25, foodCategory.getPercent());
        assertEquals(0, foodCategory.getRemainingMoney());
    }

    @Test
    public void testUpdateCategory() {
        foodCategory.addPurchase(burger);
        foodCategory.addPurchase(hotDog);
        foodCategory.updateCategory(noel);
        assertEquals(14, foodCategory.getRemainingMoney());
        assertEquals(11, foodCategory.getTotalSpent());
    }

    @Test
    public void testUpdateMoneyAvailableInCategory() {
        foodCategory.updateMoneyAvailableInCategory(noel);
        assertEquals(25, foodCategory.getRemainingMoney());
    }

    @Test
    public void testMoneyRemainingInCategory() {
        foodCategory.updateMoneyAvailableInCategory(noel);
        foodCategory.addPurchase(hotDog);
        foodCategory.addPurchase(burger);
        foodCategory.moneySpentInCategory();
        assertEquals(14, foodCategory.moneyRemainingInCategory());
    }

    @Test
    public void testResetTotalSpent() {
        foodCategory.addPurchase(hotDog);
        foodCategory.updateCategory(noel);
        foodCategory.resetTotalSpent();
        foodCategory.addPurchase(burger);
        foodCategory.updateCategory(noel);
        assertEquals(14.0, foodCategory.getRemainingMoney());
    }

    @Test
    public void testMoneySpentInCategory() {
        foodCategory.addPurchase(burger);
        foodCategory.addPurchase(hotDog);
        assertEquals(11, foodCategory.moneySpentInCategory());
    }

    @Test
    public void testAddPurchase() {
        assertEquals("Purchase successfully added.",
                foodCategory.addPurchase(hotDog));
        assertEquals(1, foodCategory.getListPurchases().size());
        assertTrue(foodCategory.getListPurchases().contains(hotDog));
    }

    @Test
    public void testRemovePurchaseSuccess() {
        foodCategory.addPurchase(hotDog);
        assertEquals("Purchase successfully removed",
                foodCategory.removePurchase(hotDog));
        assertFalse(foodCategory.getListPurchases().contains(hotDog));
        assertEquals(0, foodCategory.getListPurchases().size());
    }

    @Test
    public void testRemovePurchaseFailure() {
        foodCategory.addPurchase(burger);
        assertEquals("Unable to successfully remove purchase.",
                foodCategory.removePurchase(hotDog));
        assertTrue(foodCategory.getListPurchases().contains(burger));
        assertEquals(1, foodCategory.getListPurchases().size());
    }

    @Test
    public void testGetTotalSpent() {
        assertEquals(0, foodCategory.getTotalSpent());
    }
}

//    @Test
//    public void testFeedbackRemovePurchaseSuccess() {
//    foodCategory.addPurchase(hotDog);
//    assertEquals("Purchase successfully removed",
//            foodCategory.feedbackRemovePurchase(removePurchase(hotDog));
//    assertEquals(0, foodCategory.getListPurchases().size());
//    assertFalse(foodCategory.getListPurchases().contains(hotDog));
//    }
//
//    @Test
//    public void testFeedbackRemovePurchaseFailure() {
//    foodCategory.addPurchase(hotDog);
//    foodCategory.removePurchase(burger);
//    assertEquals(1, foodCategory.getListPurchases().size());
//    assertTrue(foodCategory.getListPurchases().contains(hotDog));
//    }
//}