package persistence;

import model.Category;
import model.Purchase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterCategoryTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Category category1 = new Category("My Category", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCategory() {
        try {
            Category category1 = new Category("My Category", 0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCategory.json");
            writer.open();
            writer.write(category1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCategory.json");
            category1 = reader.readCategory();
            assertEquals("My Category", category1.getName());
            assertEquals(0, category1.getListPurchases().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCategory() {
        try {
            Category category1 = new Category("My Category", 0);
            category1.addPurchase(new Purchase("burger", 5));
            category1.addPurchase(new Purchase("pizza", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCategory.json");
            writer.open();
            writer.write(category1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCategory.json");
            category1 = reader.readCategory();
            assertEquals("My Category", category1.getName());
            List<Purchase> purchases = category1.getListPurchases();
            assertEquals(2, purchases.size());
            checkPurchase("burger", 5, purchases.get(0));
            checkPurchase("pizza", 3, purchases.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
