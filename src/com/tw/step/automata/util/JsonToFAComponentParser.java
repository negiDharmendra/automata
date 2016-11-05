package com.tw.step.automata.util;

import com.tw.step.automata.dfa.DFATransitionTable;
import com.tw.step.automata.nfa.NFATransitionTable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonToFAComponentParser {

    private final JSONObject jsonObject;

    public JsonToFAComponentParser(String jsonString) {

        jsonObject = new JSONObject(jsonString);
    }

    public HashSet<String> parseAlphabets() {
        HashSet<String> alphabets = new HashSet<>();
        JSONArray alphabets1 = getTuple().getJSONArray(FAutomata.ALPHABETS.value());
        alphabets1.forEach(alphabet -> alphabets.add(alphabet.toString()));
        return alphabets;
    }

    public TransitionTable parseTransitionFunction(HashMap<String, TransitionTable> transitionTables) {
        JSONObject delta = getTuple().getJSONObject(FAutomata.DELTA.value());
        TransitionTable transitionTable = transitionTables.get(jsonObject.getString(FAutomata.TYPE.value()));

        Set<String> strings = delta.keySet();
        for (String string : strings) {
            JSONObject jsonObject = delta.getJSONObject(string);
            if (parseMachineInfo().get(FAutomata.TYPE.value()).equals(FAutomata.NFA.value()))
                parseNFATransitionFunction(string, jsonObject, transitionTable);
            else
                parseDFATransitionFunction(string, jsonObject, transitionTable);
        }
        return transitionTable;
    }

    public HashMap<String, String> parseMachineInfo() {
        HashMap<String, String> machineInfo = new HashMap<>();
        machineInfo.put(FAutomata.NAME.value(), jsonObject.getString(FAutomata.NAME.value()));
        machineInfo.put(FAutomata.TYPE.value(), jsonObject.getString(FAutomata.TYPE.value()));
        return machineInfo;
    }

    public States parseFinalStates() {
        States finalState = new States();
        getTuple().getJSONArray(FAutomata.FINALSTATES.value()).forEach(state -> finalState.add(new State(state.toString())));
        return finalState;
    }

    public State parseInitialStates() {
        return new State(getTuple().getString(FAutomata.STARTSTATE.value()));
    }

    public States parseAllStates() {
        States finalState = new States();
        getTuple().getJSONArray(FAutomata.STATES.value()).forEach(state -> finalState.add(new State(state.toString())));
        return finalState;
    }


    public HashMap<String, List<String>> parseTestCases() {
        HashMap<String, List<String>> testCases = new HashMap<>();
        List<String> passCases = new ArrayList<>();
        List<String> failCases = new ArrayList<>();
        jsonObject.getJSONArray(FAutomata.PASSCASES.value()).forEach(value -> passCases.add(value.toString()));
        jsonObject.getJSONArray(FAutomata.FAILCASES.value()).forEach(value -> failCases.add(value.toString()));
        testCases.put(FAutomata.PASSCASES.value(), passCases);
        testCases.put(FAutomata.FAILCASES.value(), failCases);
        return testCases;
    }

    private void parseDFATransitionFunction(String currentState, JSONObject jsonObject, TransitionTable transitionTable) {
        for (String key : jsonObject.keySet())
            ((DFATransitionTable) transitionTable).addTransition(new State(currentState), key, new State(jsonObject.getString(key)));
    }

    private void parseNFATransitionFunction(String currentState, JSONObject jsonObject, TransitionTable transitionTable) {
        for (String key : jsonObject.keySet()) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            States states = new States();
            jsonArray.forEach(state -> states.add(new State(state.toString())));
            ((NFATransitionTable) transitionTable).addTransition(new State(currentState), key, states);
        }
    }

    private JSONObject getTuple() {
        return jsonObject.getJSONObject(FAutomata.TUPLE.value());
    }
}
