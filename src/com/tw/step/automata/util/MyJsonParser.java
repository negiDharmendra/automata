package com.tw.step.automata.util;

import com.tw.step.automata.dfa.TransitionTable;
import com.tw.step.automata.util.State;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public HashMap<String, String> extractDFAInfo(JSONObject jsonObject) {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("name", (String) jsonObject.get("name"));
        objectObjectHashMap.put("type", (String) jsonObject.get("type"));

        return objectObjectHashMap;
    }

    public HashMap<String, ArrayList<String>> extractTestCases(JSONObject jsonObject) {
        HashMap<String, ArrayList<String>> testCases = new HashMap<>();
        testCases.put("pass-cases", (ArrayList<String>) jsonObject.get("pass-cases"));
        testCases.put("fail-cases", (ArrayList<String>) jsonObject.get("fail-cases"));
        return testCases;
    }

    public HashSet<State> extractStates(JSONObject jsonObject) {
        return getStates(jsonObject, "states");
    }

    public HashSet<State> extractFinalStates(JSONObject jsonObject) {

        return getStates(jsonObject, "final-states");

    }

    public TransitionTable extractDelta(JSONObject jsonObject) {
        return createTransitionTable(jsonObject);
    }

    public HashSet<String> extractAlphabets(JSONObject jsonObject) {
        ArrayList<String> alphabets = (ArrayList<String>) jsonObject.get("alphabets");
        HashSet<String> alphabetSet = new HashSet<>();
        alphabetSet.addAll(alphabets);
        return alphabetSet;
    }

    private TransitionTable createTransitionTable(JSONObject jsonTupleObject) {

        TransitionTable transitionTable = new TransitionTable();
        HashMap<String, HashMap<String, String>> delta = (HashMap) jsonTupleObject.get("delta");

        Set<String> states = delta.keySet();

        for (String state : states) {
            HashMap<String, String> alphabetStateHashMap = delta.get(state);
            for (String alphabet : alphabetStateHashMap.keySet()) {
                transitionTable.addTransition(new State(state), alphabet, new State(alphabetStateHashMap.get(alphabet)));
            }
        }
        return transitionTable;
    }

    public State extractStartState(JSONObject jsonObject) {
        return new State((String) jsonObject.get("start-state"));
    }

    private HashSet<State> getStates(JSONObject jsonObject, String stateType) {
        ArrayList<String> states = (ArrayList<String>) jsonObject.get(stateType);
        HashSet<State> allStates = new HashSet<>();
        for (String state : states) {
            allStates.add(new State(state));
        }
        return allStates;
    }
}
