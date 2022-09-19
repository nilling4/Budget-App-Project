package ui;

import model.Category;
import model.Graph;
import model.Purchase;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Budget application
// used the TellerApp as reference for making the UI
public class BudgetApp {
    private static final String JSON_STORE_CATEGORY_1 = "./data/category1.json";
    private static final String JSON_STORE_CATEGORY_2 = "./data/category2.json";
    private static final String JSON_STORE_CATEGORY_3 = "./data/category3.json";
    private static final String JSON_STORE_GRAPH = "./data/graph.json";
    private static final String JSON_STORE_USER = "./data/user.json";
    private Graph mainGraph;
    private User mainUser;
    private Category category1;
    private Category category2;
    private Category category3;
    private Scanner input;
    private JsonWriter jsonWriterCategory1;
    private JsonReader jsonReaderCategory1;
    private JsonWriter jsonWriterCategory2;
    private JsonReader jsonReaderCategory2;
    private JsonWriter jsonWriterCategory3;
    private JsonReader jsonReaderCategory3;
    private JsonWriter jsonWriterGraph;
    private JsonReader jsonReaderGraph;
    private JsonWriter jsonWriterUser;
    private JsonReader jsonReaderUser;

    // EFFECTS: constructs user and categories then runs the budget application
    public BudgetApp() throws FileNotFoundException {
        mainGraph = new Graph(0.0);
        mainUser = new User(0, "unnamed");
        category1 = new Category("Food", 0);
        category2 = new Category("Transport", 0);
        category3 = new Category("Fun", 0);
        mainGraph.addCategory(category1);
        mainGraph.addCategory(category2);
        mainGraph.addCategory(category3);
        jsonWriterCategory1 = new JsonWriter(JSON_STORE_CATEGORY_1);
        jsonReaderCategory1 = new JsonReader(JSON_STORE_CATEGORY_1);
        jsonWriterCategory2 = new JsonWriter(JSON_STORE_CATEGORY_2);
        jsonReaderCategory2 = new JsonReader(JSON_STORE_CATEGORY_2);
        jsonWriterCategory3 = new JsonWriter(JSON_STORE_CATEGORY_3);
        jsonReaderCategory3 = new JsonReader(JSON_STORE_CATEGORY_3);
        jsonWriterGraph = new JsonWriter(JSON_STORE_GRAPH);
        jsonReaderGraph = new JsonReader(JSON_STORE_GRAPH);
        jsonWriterUser = new JsonWriter(JSON_STORE_USER);
        jsonReaderUser = new JsonReader(JSON_STORE_USER);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runBudget();
    }

    // MODIFIES: this
    // EFFECTS; processes user input
    private void runBudget() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            runCategory();
        } else if (command.equals("g")) {
            runGraph();
        } else if (command.equals("u")) {
            runUser();
        } else if (command.equals("s")) {
            saveUser();
            saveCategory1();
            saveCategory2();
            saveCategory3();
            saveGraph();
        } else if (command.equals("l")) {
            loadCategory1();
            loadCategory2();
            loadCategory3();
            loadUser();
            loadGraph();
        } else {
            System.out.println("Please select a valid option...");
        }
    }

//    // MODIFIES: this
//    // EFFECTS: initializes main
//    private void init() {
//        mainGraph = new Graph();
//        mainUser = new User(0, "unnamed");
//        category1 = new Category("Food", 0);
//        category2 = new Category("Transport", 0);
//        category3 = new Category("Fun", 0);
//        mainGraph.addCategory(category1);
//        mainGraph.addCategory(category2);
//        mainGraph.addCategory(category3);
//
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }

    // MODIFIES: this
    // EFFECTS: initializes user
    private void initUser() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes graph
    private void initGraph() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes category
    private void initCategory() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect what you want to do:");
        System.out.println("\tc -> categories");
        System.out.println("\tg -> graph");
        System.out.println("\tu -> user");
        System.out.println("\ts -> save to file");
        System.out.println("\tl -> load from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: lets you update user
    private void runUser() {
        boolean keepGoing = true;
        String command = null;

        initUser();

        while (keepGoing) {
            userMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processUserCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process command for user setting
    private void processUserCommand(String command) {
        if (command.equals("n")) {
            newUser();
        } else if (command.equals("+")) {
            increaseUser();
        } else if (command.equals("-")) {
            decreaseUser();
        } else {
            System.out.println("Please select a valid option...");
        }
    }

    // MODIFIES: this
    // EFFECTS: lets you update user
    private void userMenu() {
        System.out.println("\nSelect what you want to do:");
        System.out.println("\tn -> new user");
        System.out.println("\t+ -> increase budget");
        System.out.println("\t- -> decrease budget");
        System.out.println("\tq -> quit to main menu");
    }

    // MODIFIES: this
    // EFFECTS: lets you make new user
    private void newUser() {
        System.out.println("\nIncome?");
        double income = input.nextDouble();

        System.out.println("\nNew user name?");
        input.nextLine();
        String name = input.nextLine();

        mainUser.renameUser(name);
        mainUser.increaseMoney(income);

        System.out.println("\nHello " + name);
        printIncome(mainUser);
    }

    // MODIFIES: this
    // EFFECTS: lets you increase user income
    private void increaseUser() {
        System.out.println("\nHow much do you want to increase budget by?");
        double income = input.nextDouble();
        System.out.println(mainUser.increaseMoney(income));
        printIncome(mainUser);
    }

    // MODIFIES: this
    // EFFECTS: lets you decrease user income
    private void decreaseUser() {
        System.out.println("How much do you want to decrease budget by?");
        double income = input.nextDouble();
        System.out.println(mainUser.decreaseMoney(income, mainGraph));
        printIncome(mainUser);
    }

    // EFFECTS: prints user income to screen
    private void printIncome(User user) {
        System.out.println("Budget: " + user.getIncome());
    }

    // MODIFIES: this
    // EFFECTS: lets you run graph setting
    private void runGraph() {
        boolean keepGoing = true;
        String command = null;

        initGraph();

        while (keepGoing) {
            graphMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processGraphCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process command for graph setting
    private void processGraphCommand(String command) {
        if (command.equals("u")) {
            checkTotalGraph();
        } else {
            System.out.println("Please select a valid option...");
        }
    }

    // MODIFIES: this
    // EFFECTS: menu for graph setting
    private void graphMenu() {
        System.out.println("\nSelect what you want to do:");
        System.out.println("\tu -> check total spent");
        System.out.println("\tq -> quit to main menu");
    }

    // EFFECTS: lets you see total spending by user
    private void checkTotalGraph() {
        mainGraph.resetUltimateSpent();
        mainGraph.determineUltimateSpent();
        System.out.println(mainGraph.ultimateSpentMessage(mainUser));
    }

    // MODIFIES: this
    // EFFECTS: lets you update category
    private void runCategory() {
        boolean keepGoing = true;
        String command = null;

        initCategory();

        while (keepGoing) {
            categoryMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCategoryCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process command for category setting
    private void processCategoryCommand(String command) {
        if (command.equals("a")) {
            addPurchase();
        } else {
            System.out.println("Please select a valid option...");
        }
    }

    // MODIFIES: this
    // EFFECTS: menu for category setting
    private void categoryMenu() {
        System.out.println("\nSelect what you want to do:");
        System.out.println("\ta -> add purchase");
        System.out.println("\tq -> quit to main menu");
    }

    // EFFECTS: prompts user to select category they want to edit and returns it
    private Category selectCategory() {
        String selection = ""; // force entry into loop

        while (!(selection.equals("f") || selection.equals("t") || selection.equals("!"))) {
            System.out.println("press f for food");
            System.out.println("press t for transport");
            System.out.println("press ! for fun");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("f")) {
            return category1;
        } else if (selection.equals("t")) {
            return category2;
        } else {
            return category3;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to given category
    private void addPurchase() {
        Category selected = selectCategory();
        System.out.println("\nCost?");
        double cost = input.nextDouble();

        System.out.println("\nName of purchase?");
        input.nextLine();
        String name = input.nextLine();

        Purchase purchase = new Purchase(name, cost);
        System.out.println(selected.addPurchase(purchase));
        selected.resetTotalSpent();
        selected.updateCategory(mainUser);
        printCategory(selected);
    }

    // EFFECTS: prints the category to the screen
    private void printCategory(Category selected) {
        System.out.println("Purchased items in " + selected.getName() + " category:");
        System.out.println(" ");
        for (Purchase purchase : selected.getListPurchases()) {
            System.out.println("-" + " " + purchase.getName() + "  $" + purchase.getCost());
        }
    }

    // EFFECTS: saves category 1 to file
    private void saveCategory1() {
        try {
            jsonWriterCategory1.open();
            jsonWriterCategory1.write(category1);
            jsonWriterCategory1.close();
            System.out.println("Saved " + category1.getName() + " to " + JSON_STORE_CATEGORY_1);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_1);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads category 1 from file
    private void loadCategory1() {
        try {
            category1 = jsonReaderCategory1.readCategory();
            System.out.println("Loaded " + category1.getName() + " from " + JSON_STORE_CATEGORY_1);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CATEGORY_1);
        }
    }

    // EFFECTS: saves category 2 to file
    private void saveCategory2() {
        try {
            jsonWriterCategory2.open();
            jsonWriterCategory2.write(category2);
            jsonWriterCategory2.close();
            System.out.println("Saved " + category2.getName() + " to " + JSON_STORE_CATEGORY_2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_2);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads category 2 from file
    private void loadCategory2() {
        try {
            category2 = jsonReaderCategory2.readCategory();
            System.out.println("Loaded " + category2.getName() + " from " + JSON_STORE_CATEGORY_2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CATEGORY_2);
        }
    }

    // EFFECTS: saves category 3 to file
    private void saveCategory3() {
        try {
            jsonWriterCategory3.open();
            jsonWriterCategory3.write(category3);
            jsonWriterCategory3.close();
            System.out.println("Saved " + category3.getName() + " to " + JSON_STORE_CATEGORY_3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_3);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads category 3 from file
    private void loadCategory3() {
        try {
            category3 = jsonReaderCategory3.readCategory();
            System.out.println("Loaded " + category3.getName() + " from " + JSON_STORE_CATEGORY_3);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CATEGORY_3);
        }
    }

    // EFFECTS: saves graph to file
    private void saveGraph() {
        try {
            jsonWriterGraph.open();
            jsonWriterGraph.write(mainGraph);
            jsonWriterGraph.close();
            System.out.println("Saved " + mainGraph + " to " + JSON_STORE_GRAPH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_GRAPH);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads graph from file
    private void loadGraph() {
        try {
            mainGraph = jsonReaderGraph.readGraph();
            System.out.println("Loaded " + mainGraph + " from " + JSON_STORE_GRAPH);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_GRAPH);
        }
    }

    // EFFECTS: saves the user to file
    private void saveUser() {
        try {
            jsonWriterUser.open();
            jsonWriterUser.write(mainUser);
            jsonWriterUser.close();
            System.out.println("Saved " + mainUser.getName() + " to " + JSON_STORE_USER);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_USER);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user from file
    private void loadUser() {
        try {
            mainUser = jsonReaderUser.readUser();
            System.out.println("Loaded " + mainUser.getName() + " from " + JSON_STORE_USER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_USER);
        }
    }
}
