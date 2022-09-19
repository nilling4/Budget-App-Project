package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an item purchased with a name and cost
public class Purchase implements Writable {
    private String name;
    private double cost;

    // EFFECTS: creates a new purchase object with a name and cost
    public Purchase(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    // REQUIRES: new name cannot be ""
    // MODIFIES: this
    // EFFECTS: produces true if name successfully added, false otherwise
    public String editPurchase(String name) {
        if (name != this.name) {
            this.name = name;
            return "Purchase was successfully edited.";
        } else {
            return "Error: Purchase could not be edited.";
        }
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);
        return json;
    }
}
