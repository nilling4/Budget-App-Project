package persistence;

import model.Category;
import model.Purchase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderCategoryTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Category category = reader.readCategory();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCategory1() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCategory1.json");
        try {
            Category category1 = reader.readCategory();
            assertEquals("My Category", category1.getName());
            assertEquals(0, category1.getListPurchases().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCategory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCategory.json");
        try {
            Category category1 = reader.readCategory();
            assertEquals("My Category", category1.getName());
            List<Purchase> purchases = category1.getListPurchases();
            assertEquals(2, purchases.size());
            checkPurchase("burger", 5, purchases.get(0));
            checkPurchase("pizza", 3, purchases.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
