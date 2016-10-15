package com.dharmenn.dfa;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

class MyJsonParser {
    JSONArray parse(String jsonData) throws net.minidev.json.parser.ParseException {
        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);

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
