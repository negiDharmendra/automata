import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyJsonParser {
    public JSONArray parse(String jsonData) throws IOException, net.minidev.json.parser.ParseException {
        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
        String s1 = Files.readAllLines(Paths.get("src/examples.json")).iterator().next();
        s1 = s1.substring(1, s1.length() - 1).replace('\\', ' ').replace(" \"", "\"");
        Object obj = parser.parse(jsonData);
        if (!obj.getClass().getName().contains("JSONArray")) {
            JSONArray objects = new JSONArray();
            objects.add(obj);
            return objects;
        } else {
            return (JSONArray) obj;
        }
    }
}
