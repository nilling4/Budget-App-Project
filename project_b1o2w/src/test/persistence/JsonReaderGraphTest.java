package persistence;

import model.Category;
import model.Graph;
import model.Purchase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderGraphTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Graph graph = reader.readGraph();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGraph() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGraph.json");
        try {
            Graph graph = reader.readGraph();
            assertEquals(0, graph.getUltimateSpent());
            assertEquals(0, graph.getCategoryList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGraph() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGraph.json");
        try {
            Graph graph = reader.readGraph();
            assertEquals(0, graph.getUltimateSpent());
            List<Category> categories = graph.getCategoryList();
            assertEquals(2, categories.size());
            checkCategory("food", 0, categories.get(0));
            checkCategory("transport", 0, categories.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
