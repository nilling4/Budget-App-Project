package persistence;

import model.Category;
import model.Graph;
import model.Purchase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterGraphTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Graph graph = new Graph(0.0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGraph() {
        try {
            Graph graph = new Graph(0.0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGraph.json");
            writer.open();
            writer.write(graph);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGraph.json");
            graph = reader.readGraph();
            assertEquals(0, graph.getUltimateSpent());
            assertEquals(0, graph.getCategoryList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGraph() {
        try {
            Graph graph = new Graph(0.0);
            graph.addCategory(new Category("food", 0));
            graph.addCategory(new Category("transport", 0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGraph.json");
            writer.open();
            writer.write(graph);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGraph.json");
            graph = reader.readGraph();
            assertEquals(0, graph.getUltimateSpent());
            List<Category> categories = graph.getCategoryList();
            assertEquals(2, categories.size());
            checkCategory("food", 0, categories.get(0));
            checkCategory("transport", 0, categories.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
