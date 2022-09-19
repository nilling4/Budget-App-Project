package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User noel;
    private Graph testGraph;

    @BeforeEach
    public void setUp() {
        noel = new User(100, "Noel");
        testGraph = new Graph((double) 0);
    }

    @Test
    public void testUser() {
        assertEquals("Noel", noel.getName());
        assertEquals(100, noel.getIncome());
    }

    @Test
    public void testIncreaseMoneySuccess() {
        assertEquals("money increased by: 50.0", noel.increaseMoney(50));
        assertEquals(150, noel.getIncome());
    }

    @Test
    public void testIncreaseMoneyFailure() {
        assertEquals("money could not be increased", noel.increaseMoney(-50));
        assertEquals(100, noel.getIncome());
    }

    @Test
    public void testDecreaseMoneySuccess() {
        assertEquals("money decreased by: 50.0", noel.decreaseMoney(50, testGraph));
        assertEquals(50.0, noel.getIncome());
    }

    @Test
    public void testDecreaseMoneyFailure() {
        assertEquals("money could not be decreased", noel.decreaseMoney(150, testGraph));
        assertEquals(100, noel.getIncome());

    }

    @Test
    public void testRenameUser() {
        noel.renameUser("Henry");
        assertEquals("Henry", noel.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Noel", noel.getName());
    }

    @Test
    public void testGetIncome() {
        assertEquals(100, noel.getIncome());
    }
}
