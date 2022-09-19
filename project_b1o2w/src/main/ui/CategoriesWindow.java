package ui;

import model.*;
import model.Event;
import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


// Represents the CategoriesWindow that is opened when running application
// class where you can add purchases items to categories, load/save files, create new user
public class CategoriesWindow implements ActionListener, KeyListener {

    private static final String JSON_STORE_CATEGORY_1 = "./data/category1.json";
    private static final String JSON_STORE_CATEGORY_2 = "./data/category2.json";
    private static final String JSON_STORE_CATEGORY_3 = "./data/category3.json";
    private static final String JSON_STORE_GRAPH = "./data/graph.json";
    private static final String JSON_STORE_USER = "./data/user.json";
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
    private JFrame frame;
    private JTable table;
    private JLabel label;
    private JTextField textName;
    private JTextField textCost;
    private JButton buttonAdd;
    private JComboBox comboBox;
    private String[] categories = {"food", "fun", "transport"};
    private String[] row;
    private DefaultTableModel model;
    private Purchase purchase;
    private String comboBoxSelectedItem;
    private Category foodCategory = new Category("food", 0);
    private Category funCategory = new Category("fun", 0);
    private Category transportCategory = new Category("transport", 0);
    private Graph mainGraph = new Graph(0.0);
    private User mainUser;
    private NewUserWindow newUserWindow;
    private JButton userButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton calculateButton;
    private Font font;
    private JLabel ultimateLabel;
    private double totalSpent;



    // EFFECTS: runs main window
    public static void main(String[] args) {
        CategoriesWindow categoriesWindow = new CategoriesWindow();
    }

    // EFFECTS: creates a new categories window with a JScroll Pane, an array of Strings to set row data
    // set frame size and adds JTextFields to JFrame
    public CategoriesWindow() {
        newObjects();
        setTable();

        buttonSetUp();
        setImage();

        frame.add(comboBox);
        textName.setBounds(20, 220, 100, 25);
        textCost.setBounds(20, 250, 100, 25);
        comboBox.setBounds(20, 280, 100, 25);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);
        frame.add(pane);

        frame.add(textName);
        frame.add(textCost);
        setWindowClose();

        row = new String[3];

        selectPanel();
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: prints out log event when closing window
    public void setWindowClose() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets the model to the table and changes the JTable background color, font size,
    // color, and row height
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the model to the table and changes the JTable background color, font size,
    // color, and row height
    public void setTable() {
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);

        table.setFont(font);
        table.setRowHeight(30);
    }

    // MODIFIES: this
    // EFFECTS: adds/resize image to JFrame
    public void setImage() {
        ImageIcon budgetIcon = new ImageIcon(getClass().getResource("budget-icon-10.png"));
        Image budgetImage = budgetIcon.getImage();
        Image modifiedBudgetImage = budgetImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        budgetIcon = new ImageIcon(modifiedBudgetImage);

        JLabel labelImage = new JLabel(budgetIcon);
        frame.add(labelImage);
        labelImage.setBounds(650, 175, 200, 200);
    }

    // MODIFIES: this
    // EFFECTS: creates new frames, table, labels, JComboBox, JTextFields, font, user
    public void newObjects() {
        frame = new JFrame("UniSave");
        table = new JTable();
        label = new JLabel("Select from an option below.");
        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        setJson();
        mainGraph.addCategory(foodCategory);
        mainGraph.addCategory(funCategory);
        mainGraph.addCategory(transportCategory);

        comboBox = new JComboBox(categories);

        Object[] columns = {"Item Name","Cost", "Category"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        textName = new JTextField();
        textCost = new JTextField();
        textCost.addKeyListener(this);

        font = new Font("",1,22);
        mainUser = new User(0, "");
    }

    // MODIFIES: this
    // EFFECTS: creates new Json objects
    public void setJson() {
        jsonWriterCategory1 = new JsonWriter(JSON_STORE_CATEGORY_1);
        jsonReaderCategory1 = new JsonReader(JSON_STORE_CATEGORY_1);
        jsonWriterCategory2 = new JsonWriter(JSON_STORE_CATEGORY_2);
        jsonReaderCategory2 = new JsonReader(JSON_STORE_CATEGORY_2);
        jsonWriterCategory3 = new JsonWriter(JSON_STORE_CATEGORY_3);
        jsonReaderCategory3 = new JsonReader(JSON_STORE_CATEGORY_3);
        jsonWriterGraph = new JsonWriter(JSON_STORE_GRAPH);
        jsonReaderGraph = new JsonReader(JSON_STORE_GRAPH);

    }

    // MODIFIES: this
    // EFFECTS: creates new buttons and sets them to frame
    public void buttonSetUp() {
        // create JButtons
        buttonAdd = new JButton("Add");
        userButton = new JButton("User");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        calculateButton = new JButton("Calculate");

        buttonAdd.setBounds(150, 220, 100, 25);
        userButton.setBounds(150, 250, 100, 25);
        saveButton.setBounds(150, 280, 100, 25);
        loadButton.setBounds(150, 310, 100, 25);
        calculateButton.setBounds(280, 220, 100, 25);

        setUltimateLabel();
        // add JButtons to the jframe
        frame.add(buttonAdd);
        frame.add(userButton);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(calculateButton);
        frame.add(ultimateLabel);

        userButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        calculateButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: action listener for JComboBox
    public void selectPanel() {
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxSelectedItem = (String) comboBox.getSelectedItem();
                row[2] = comboBoxSelectedItem;
            }
        });
        addButton();

    }

    // MODIFIES: this
    // EFFECTS: sets up ultimate spent message
    public void setUltimateLabel() {
        mainGraph.resetUltimateSpent();
        mainGraph.determineUltimateSpent();
        String message = mainGraph.ultimateSpentMessage(mainUser);
        ultimateLabel = new JLabel(message);

        ultimateLabel.setBounds(410, 220, 300, 25);
    }


    // MODIFIES: this
    // EFFECTS: sets main user to given user
    public void setUser(User user) {
        mainUser = user;
    }

    // MODIFIES: this
    // EFFECTS: action listener for add button, adds Purchase field data to each row of JTable
    public void addButton() {
        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textName.getText();
                row[1] = textCost.getText();

                model.addRow(row);
                purchase = new Purchase(textName.getText(), Double.parseDouble(textCost.getText()));
                    if (Objects.equals(comboBoxSelectedItem, "food")) {
                        addFoodPurchase();
                    } else if (Objects.equals(comboBoxSelectedItem, "fun")) {
                        addFunPurchase();
                    } else {
                        addTransportPurchase();
                    }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to food category and updates total spent
    public void addFoodPurchase() {
        foodCategory.addPurchase(purchase);
        foodCategory.resetTotalSpent();
        foodCategory.updateCategory(mainUser);
        totalSpent += purchase.getCost();
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to fun category and updates total spent
    public void addFunPurchase() {
        funCategory.addPurchase(purchase);
        funCategory.resetTotalSpent();
        funCategory.updateCategory(mainUser);
        totalSpent += purchase.getCost();
    }

    // MODIFIES: this
    // EFFECTS: adds transport to food category and updates total spent
    public void addTransportPurchase() {
        transportCategory.addPurchase(purchase);
        transportCategory.resetTotalSpent();
        transportCategory.updateCategory(mainUser);
        totalSpent += purchase.getCost();
    }

    // MODIFIES: this
    // EFFECTS: button action listener for user, save, load, calculate
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            newUserWindow = new NewUserWindow(this);
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == loadButton) {
            jsonReaderUser = new JsonReader(JSON_STORE_USER);
            load();
            loadTableFood();
            loadTableFun();
            loadTableTransport();
        } else if (e.getSource() == calculateButton) {
            mainGraph.resetUltimateSpent();
            mainGraph.determineUltimateSpent();
            String message = "You have spent " + totalSpent + " of your "
                    + mainUser.getIncome() + " total dollars.";
            ultimateLabel.setText(message);
        }
    }

    // EFFECTS: save helper function
    public void save() {
        saveUser();
        saveFoodCategory();
        saveFunCategory();
        saveTransportCategory();
        saveGraph();
    }

    // EFFECTS: load helper function
    public void load() {
        loadUser();
        loadFoodCategory();
        loadFunCategory();
        loadTransportCategory();
        loadGraph();
    }

    // MODIFIES: this
    // EFFECTS: adds food purchases to table
    public void loadTableFood() {
        for (Purchase purchase : foodCategory.getListPurchases()) {
            row[0] = purchase.getName();
            row[1] = Double.toString(purchase.getCost());
            row[2] = "food";

            // add row to the model
            model.addRow(row);
            totalSpent += purchase.getCost();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds fun purchases to table
    public void loadTableFun() {
        for (Purchase purchase : funCategory.getListPurchases()) {
            row[0] = purchase.getName();
            row[1] = Double.toString(purchase.getCost());
            row[2] = "fun";

            // add row to the model
            model.addRow(row);
            totalSpent += purchase.getCost();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds transport purchases to table
    public void loadTableTransport() {
        for (Purchase purchase : transportCategory.getListPurchases()) {
            row[0] = purchase.getName();
            row[1] = Double.toString(purchase.getCost());
            row[2] = "transport";

            // add row to the model
            model.addRow(row);
            totalSpent += purchase.getCost();
        }
    }

    // EFFECTS: saves the user to file
    public void saveUser() {
        jsonWriterUser = new JsonWriter(JSON_STORE_USER);
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
    public void loadUser() {
        jsonReaderUser = new JsonReader(JSON_STORE_USER);
        try {
            mainUser = jsonReaderUser.readUser();
            System.out.println("Loaded " + mainUser.getName() + " from " + JSON_STORE_USER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_USER);
        }
    }

    // EFFECTS: saves food category to file
    private void saveFoodCategory() {
        try {
            jsonWriterCategory1.open();
            jsonWriterCategory1.write(foodCategory);
            jsonWriterCategory1.close();
            System.out.println("Saved " + foodCategory.getName() + " to " + JSON_STORE_CATEGORY_1);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_1);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads food category from file
    private void loadFoodCategory() {
        try {
            foodCategory = jsonReaderCategory1.readCategory();
            System.out.println("Loaded " + foodCategory.getName() + " from " + JSON_STORE_CATEGORY_1);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CATEGORY_1);
        }
    }

    // EFFECTS: saves fun category to file
    private void saveFunCategory() {
        try {
            jsonWriterCategory2.open();
            jsonWriterCategory2.write(funCategory);
            jsonWriterCategory2.close();
            System.out.println("Saved " + funCategory.getName() + " to " + JSON_STORE_CATEGORY_2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_2);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads fun category from file
    private void loadFunCategory() {
        try {
            funCategory = jsonReaderCategory2.readCategory();
            System.out.println("Loaded " + funCategory.getName() + " from " + JSON_STORE_CATEGORY_2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CATEGORY_2);
        }
    }

    // EFFECTS: saves transport category to file
    private void saveTransportCategory() {
        try {
            jsonWriterCategory3.open();
            jsonWriterCategory3.write(transportCategory);
            jsonWriterCategory3.close();
            System.out.println("Saved " + transportCategory.getName() + " to " + JSON_STORE_CATEGORY_3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CATEGORY_3);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads transport category from file
    private void loadTransportCategory() {
        try {
            transportCategory = jsonReaderCategory3.readCategory();
            System.out.println("Loaded " + transportCategory.getName() + " from " + JSON_STORE_CATEGORY_3);
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

    // MODIFIES: this
    // EFFECTS: enter key action listener
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            row[0] = textName.getText();
            row[1] = textCost.getText();

            model.addRow(row);
            purchase = new Purchase(textName.getText(), Double.parseDouble(textCost.getText()));
            if (Objects.equals(comboBoxSelectedItem, "food")) {
                addFoodPurchase();
            } else if (Objects.equals(comboBoxSelectedItem, "fun")) {
                addFunPurchase();
            } else {
                addTransportPurchase();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


}

