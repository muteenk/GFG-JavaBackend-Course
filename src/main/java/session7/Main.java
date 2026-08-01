package session7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main {

    static void main() {
        System.out.println("Hello");

        JsonObject myJson = new JsonObject();
        myJson.addProperty("name", "John");
        myJson.addProperty("age", 30);
        String jsonString = myJson.toString();

        System.out.println(jsonString);
    }

}
