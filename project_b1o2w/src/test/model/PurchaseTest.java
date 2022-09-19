package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseTest {
    private Purchase hotDog;
    private Purchase burger;
    private double cost;

    @BeforeEach
    public void setUp() {
        hotDog = new Purchase("Hot Dog", 5);
        burger = new Purchase("Burger", 6);
    }

    @Test
    public void testPurchase() {
        assertEquals(5, hotDog.getCost());
        assertEquals("Hot Dog", hotDog.getName());
    }

    @Test
    public void testEditPurchaseSuccess() {
        assertEquals("Purchase was successfully edited.",
                hotDog.editPurchase("better hotdog"));
        assertEquals("better hotdog", hotDog.getName());
    }

    @Test
    public void testEditPurchaseFailure() {
        assertEquals("Error: Purchase could not be edited.",
                hotDog.editPurchase("Hot Dog"));
        assertEquals("Hot Dog", hotDog.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Hot Dog", hotDog.getName());
    }

    @Test
    public void testGetCost() {
        assertEquals(5, hotDog.getCost());
    }
}
