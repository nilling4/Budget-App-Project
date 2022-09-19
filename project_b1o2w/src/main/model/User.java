package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents users income
public class User implements Writable {

    private double income;
    private String name;

    // EFFECTS: creates a new user
    public User(double income, String name) {
        this.income = income;
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: renames a user
    public void renameUser(String name) {
        this.name = name;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: Increases money by amount
    public String increaseMoney(double amount) {
        if (amount > 0) {
            this.income += amount;
            return "money increased by: " + amount;
        } else {
            return "money could not be increased";
        }
    }

    // REQUIRES: money - amount > ultimateSpent
    // MODIFIES: this
    // EFFECTS: decreases money by amount
    public String decreaseMoney(double amount, Graph graph) {
        if (this.income - amount > graph.getUltimateSpent()) {
            this.income -= amount;
            return "money decreased by: " + amount;
        } else {
            return "money could not be decreased";
        }
    }

    public double getIncome() {
        return this.income;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("income", income);
        json.put("name", name);
        return json;
    }

//    // EFFECTS: returns things in this category as a JSON array
//    private JSONArray userToJson() {
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(this.toJson());
//        return jsonArray;
//    }
}
