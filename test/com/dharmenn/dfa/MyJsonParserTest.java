package com.dharmenn.dfa;
import net.minidev.json.JSONArray;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MyJsonParserTest {
    @Test
    public void shouldReturnParsedJsonTextAsJsonArray() throws Exception {
        String jsonText = "[{\"name\":\"bar\"}]";
        JSONArray parse = new MyJsonParser().parse(jsonText);
        assertEquals("bar", ((HashMap<String, String>) parse.get(0)).get("name"));
    }

    @Test
    public void shouldReturnParsedJsonTextAsJsonArrayEvenThereIsOnlyOneJsonObject() throws Exception {
        String jsonText = "{\"name\":\"bar\"}";
        JSONArray parse = new MyJsonParser().parse(jsonText);
        assertEquals("bar", ((HashMap<String, String>) parse.get(0)).get("name"));
    }

    @Test
    public void shouldReturnFirstJsonObject() throws Exception {
        String jsonText = "{\"name\":\"bar\"},{\"name\":\"bar\"}";
        JSONArray parse = new MyJsonParser().parse(jsonText);
        assertEquals(1, parse.size());
        assertEquals("bar", ((HashMap<String, String>) parse.get(0)).get("name"));
    }
}