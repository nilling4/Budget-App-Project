package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    private Graph testGraph;
    private Category foodCategory;
    private Category transportCategory;
    private Purchase hotDog;
    private Purchase bike;
    private User noel;

    @BeforeEach
    public void setUp() {
        testGraph = new Graph(0.0);
        foodCategory = new Category("Food", 20);
        transportCategory = new Category("Transport", 30);
        hotDog = new Purchase("Hot Dog", 5);
        bike = new Purchase("Bike", 25);
        noel = new User(100, "Noel");
    }

    @Test
    public void setTestGraph() {
        assertEquals(0, testGraph.getCategoryList().size());
        assertEquals(0, testGraph.getUltimateSpent());
    }

    @Test
    public void testAddCategorySuccess() {
        testGraph.addCategory(foodCategory);
        assertEquals(1, testGraph.getCategoryList().size());
        assertTrue(testGraph.getCategoryList().contains(foodCategory));
    }

    @Test
    public void testAddCategoryFailure() {
        testGraph.addCategory(foodCategory);
        assertEquals("Category wasn't added", testGraph.addCategory(foodCategory));
        assertEquals(1, testGraph.getCategoryList().size());
        assertTrue(testGraph.getCategoryList().contains(foodCategory));
    }

    @Test
    public void testRemoveCategorySuccess() {
        testGraph.addCategory(foodCategory);
        testGraph.addCategory(transportCategory);
        testGraph.removeCategory(foodCategory);
        assertEquals(1, testGraph.getCategoryList().size());
        assertTrue(testGraph.getCategoryList().contains(transportCategory));
    }

    @Test
    public void testRemoveCategoryFailure() {
        testGraph.addCategory(transportCategory);
        assertEquals("Category was not removed.",
                testGraph.removeCategory(foodCategory));
    }

    @Test
    public void testDetermineUltimateSpent() {
        transportCategory.addPurchase(bike);
        transportCategory.updateCategory(noel);
        foodCategory.addPurchase(hotDog);
        foodCategory.updateCategory(noel);
        testGraph.addCategory(foodCategory);
        testGraph.addCategory(transportCategory);
        assertEquals(30, testGraph.determineUltimateSpent());
    }

    @Test
    public void testResetUltimateSpent() {
        testGraph.resetUltimateSpent();
        assertEquals(0, testGraph.getUltimateSpent());
    }

    @Test
    public void testUltimateSpentMessage() {
        transportCategory.addPurchase(bike);
        transportCategory.updateCategory(noel);
        testGraph.addCategory(transportCategory);
        testGraph.determineUltimateSpent();
        assertEquals("You have spent 25.0 of your 100.0 total dollars.",
                testGraph.ultimateSpentMessage(noel));
    }

    @Test
    public void testCategoryMessage() {
        transportCategory.addPurchase(bike);
        transportCategory.updateCategory(noel);
        testGraph.addCategory(transportCategory);
        assertEquals("Transport: 5.0 dollars remaining",
                testGraph.categoryMessage(transportCategory));
    }

    @Test
    public void testGetUltimateSpent() {
        assertEquals(0, testGraph.getUltimateSpent());
    }

    @Test
    public void testGetCategoryList() {
        assertEquals(0, testGraph.getCategoryList().size());
    }
}
