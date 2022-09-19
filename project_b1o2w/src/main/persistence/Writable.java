package persistence;

import netscape.javascript.JSObject;
import org.json.JSONObject;

// Code modeled after JsonSerializationDemo shown on EdX
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
