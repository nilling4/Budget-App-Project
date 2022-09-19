package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Represents a NewUserWindow class where you can create a new user by clicking
// set user button or by pressing enter after filling in text fields
public class NewUserWindow implements ActionListener, KeyListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel nameLabel;
    private JTextField incomeText;
    private JLabel incomeLabel;
    private JTextField nameText;
    private JButton button;
    private User newUser;
    private JLabel message;
    private CategoriesWindow categoriesWindow;

    // MODIFIES: this
    // EFFECTS: creates a new user window that takes in a categories window
    public NewUserWindow(CategoriesWindow categoriesWindow) {
        this.categoriesWindow = categoriesWindow;
        frameSetUp();
    }

    // MODIFIES: this
    // EFFECTS: creates/sets up a new frame, panel
    public void frameSetUp() {
        frame = new JFrame("New User");
        panel = new JPanel();
        frame.setSize(300, 220);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        nameLabelSetUp();
        incomeLabelSetUp();
        incomeTextSetUp();
        setNameText();
        setButton();
        messageLabel();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates name JTextField
    public void setNameText() {
        nameText = new JTextField(10);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);
    }

    // MODIFIES: this
    // EFFECTS: creates name JLabel
    public void nameLabelSetUp() {
        nameLabel = new JLabel("User");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates income JLabel
    public void incomeLabelSetUp() {
        incomeLabel = new JLabel("Income");
        incomeLabel.setBounds(10, 50, 80, 25);
        panel.add(incomeLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates income JTextField
    public void incomeTextSetUp() {
        incomeText = new JTextField(20);
        incomeText.setBounds(100, 50, 165, 25);
        panel.add(incomeText);
        incomeText.addKeyListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates set button
    public void setButton() {
        button = new JButton("Set User");
        button.setBounds(10, 80, 110, 25);
        button.addActionListener(this);
        panel.add(button);
    }

    public String getName() {
        return nameLabel.getText();
    }

    public double getIncome() {
        return Double.parseDouble(incomeText.getText());
    }

    // MODIFIES: this
    // EFFECTS: creates message JLabel
    public void messageLabel() {
        message = new JLabel("");
        message.setBounds(10, 120, 200, 25);
        panel.add(message);
    }

    // MODIFIES: this
    // EFFECTS: set button action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameText.getText();
        double income = Double.parseDouble(incomeText.getText());
        newUser = new User(income, name);
        categoriesWindow.setUser(newUser);
        System.out.println(name + " , " + income);

        message.setText("User: " + name + "         Income: " + income);
    }

    // MODIFIES: this
    // EFFECTS: enter key action listener
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String name = nameText.getText();
            double income = Double.parseDouble(incomeText.getText());
            newUser = new User(income, name);
            categoriesWindow.setUser(newUser);
            System.out.println(name + " , " + income);

            message.setText("User: " + name + "         Income: " + income);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


}
