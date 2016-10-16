package com.dharmenn.dfa;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MyJsonParserTest {

    private JSONObject jsonObject;
    private MyJsonParser myJsonParser;

    @Before
    public void setUp() throws Exception {
        String jsonString = "{\"name\":\"odd number of zeroes\",\"type\":\"dfa\"," +
                "\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"]," +
                "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
                "\"pass-cases\":[\"0\",\"000\"]," +
                "\"fail-cases\":[\"00\",\"0000\"]" +
                "\"start-state\":\"q1\",\"final-states\":[\"q2\"]}}";

        myJsonParser = new MyJsonParser();
        jsonObject = (JSONObject) myJsonParser.parse(jsonString).get(0);

    }

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

    @Test
    public void shouldExtractTupleFromGivenJsonObject() throws ParseException {

        Tuple tuple = myJsonParser.extractTuple(jsonObject);

        assertEquals("com.dharmenn.dfa.Tuple", tuple.getClass().getName());
    }


    @Test
    public void shouldExtractDfaInformation() throws ParseException {

        HashMap<String, String> stringStringHashMap = myJsonParser.extractDFAInfo(jsonObject);

        assertEquals("odd number of zeroes", stringStringHashMap.get("name"));
        assertEquals("dfa", stringStringHashMap.get("type"));
    }

    @Test
    public void shouldExtractDfaTestCases() throws ParseException {

        ArrayList<String> expectedPassCases = new ArrayList<>();
        expectedPassCases.add("0");
        expectedPassCases.add("000");

        ArrayList<String> expectedFailCases = new ArrayList<>();
        expectedFailCases.add("00");
        expectedFailCases.add("0000");

        HashMap<String, ArrayList<String>> stringStringHashMap = myJsonParser.extractTestCases(jsonObject);

        assertEquals(expectedPassCases, stringStringHashMap.get("pass-cases"));
        assertEquals(expectedFailCases, stringStringHashMap.get("fail-cases"));
    }
}