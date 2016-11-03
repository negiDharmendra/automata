package com.tw.step.automata.util;

import org.json.JSONObject;

public class JsonToFAComponentParser {

    private final JSONObject jsonObject;

    public JsonToFAComponentParser(String jsonString) {
        jsonObject = new JSONObject(jsonString);
    }


    String parseName() {
        return jsonObject.getString("name");
    }
}
