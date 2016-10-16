package com.dharmenn.dfa;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MyJsonParser {
    public JSONArray parse(String jsonData) throws net.minidev.json.parser.ParseException {
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

    public Tuple extractTuple(JSONObject jsonObject) {
        JSONObject jsonTupleObject = (JSONObject)jsonObject.get("tuple");
        ArrayList<String> states = (ArrayList<String>) jsonTupleObject.get("states");
        Tuple tuple = new Tuple();

        tuple.addStartState(new State((String) jsonTupleObject.get("start-state")));

        for (String state : states) {
            tuple.addState(new State(state));
        }


        ArrayList<String> finalStates = (ArrayList<String>) jsonTupleObject.get("final-states");

        for (String finalState : finalStates) {
            tuple.addFinalState(new State(finalState));
        }


        ArrayList<String> alphabets = (ArrayList<String>) jsonTupleObject.get("alphabets");

        for (String alphabet : alphabets) {
            tuple.addAlphabet(alphabet);
        }

        tuple.addTransitionTable( createTransitionTable(jsonTupleObject));


        return tuple;
    }

    public HashMap<String, String> extractDFAInfo(JSONObject jsonObject) {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("name", (String) jsonObject.get("name"));
        objectObjectHashMap.put("type", (String) jsonObject.get("type"));

        return objectObjectHashMap;
    }

    public HashMap<String, ArrayList<String>> extractTestCases(JSONObject jsonObject) {
        HashMap<String, ArrayList<String>> testCases = new HashMap<>();
        testCases.put("pass-cases",(ArrayList<String>) jsonObject.get("pass-cases"));
        testCases.put("fail-cases",(ArrayList<String>) jsonObject.get("fail-cases"));
        return testCases;
    }

    private TransitionTable createTransitionTable(JSONObject jsonTupleObject) {
        TransitionTable transitionTable = new TransitionTable();
        HashMap<String, HashMap<String, String>> delta = (HashMap) jsonTupleObject.get("delta");

        Set<String> strings = delta.keySet();

        for (String state : strings) {
            HashMap<String, String> alphabetSatteHashMap = delta.get(state);
            for (String alphabet : alphabetSatteHashMap.keySet()) {
                transitionTable.addTransition(new State(state), alphabet, new State(alphabetSatteHashMap.get(alphabet)));
            }
        }
        return transitionTable;
    }
}
