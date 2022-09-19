package persistence;

import model.Category;
import model.Graph;
import model.Purchase;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code modeled after JsonSerializationDemo shown on EdX
// Represents a reader that reads category and user from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads category from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Category readCategory() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCategory(jsonObject);
    }

    // EFFECTS: reads graph from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Graph readGraph() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGraph(jsonObject);
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User readUser() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses category from JSON object and returns it
    private Category parseCategory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int percent = jsonObject.getInt("percent");
        Category category = new Category(name, percent);
        addPurchasesCategory(category, jsonObject);
        return category;
    }

    // EFFECTS: parses graph from JSON object and returns it
    private Graph parseGraph(JSONObject jsonObject) {
        double ultimateSpent = jsonObject.getDouble("ultimateSpent");
        Graph graph = new Graph(ultimateSpent);
        addCategoriesGraph(graph, jsonObject);
        return graph;
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double income = jsonObject.getDouble("income");
        User user = new User(income, name);
        addUser(user, jsonObject);
        return user;
    }

    // MODIFIES: category
    // EFFECTS: parses thingies from JSON object and adds them to Category
    private void addPurchasesCategory(Category category, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listPurchases");
        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            addPurchaseCategory(category, nextPurchase);
        }
    }

    // MODIFIES: category
    // EFFECTS: parses thingies from JSON object and adds them to Category
    private void addCategoriesGraph(Graph graph, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("categoryList");
        for (Object json : jsonArray) {
            JSONObject nextCategory = (JSONObject) json;
            addCategoryGraph(graph, nextCategory);
        }
    }

//    // MODIFIES: wr
//    // EFFECTS: parses thingies from JSON object and adds them to user
//    private void addThingiesUser(User user, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
//        for (Object json : jsonArray) {
//            JSONObject nextThingy = (JSONObject) json;
//            addThingyUser(wr, nextThingy);
//        }
//    }

    // MODIFIES: category
    // EFFECTS: parses thingy from JSON object and adds it to category
    private void addPurchaseCategory(Category category, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double cost = jsonObject.getDouble("cost");
        Purchase purchase = new Purchase(name, cost);
        category.addPurchase(purchase);
    }

    // MODIFIES: category
    // EFFECTS: parses thingy from JSON object and adds it to category
    private void addCategoryGraph(Graph graph, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int percent = jsonObject.getInt("percent");
        Category category = new Category(name, percent);
        graph.addCategory(category);
    }

    // MODIFIES: user
    // EFFECTS: parses thingy from JSON object and adds it to user
    private void addUser(User wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double income = jsonObject.getDouble("income");
        //Category category = Category.valueOf(jsonObject.getString("category"));
        User user = new User(income, name);
        //user.addThingy(thingy);
    }
}
