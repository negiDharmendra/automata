package com.dharmenn.dfaTestRunner;

import com.dharmenn.dfa.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFATestRunner {
    public void run(String jsonFileName, DFAGenerator dfaGenerator) throws IOException, ParseException, InvalidAlphabetException {
        String jsonText = Files.readAllLines(Paths.get(jsonFileName)).iterator().next();
        jsonText = jsonText.replaceAll("\\\\", "");
        jsonText = jsonText.substring(1, jsonText.length() - 1);
        MyJsonParser myJsonParser = new MyJsonParser();
        JSONArray parse = myJsonParser.parse(jsonText);
        System.out.printf(String.valueOf(parse));
        for (Object o : parse) {
            JSONObject next = (JSONObject) o;

            HashMap<String, String> stringStringHashMap = myJsonParser.extractDFAInfo(next);

            JSONObject tuple = (JSONObject) next.get("tuple");

            HashSet<State> states = myJsonParser.extractStates(tuple);
            HashSet<String> alphabets = myJsonParser.extractAlphabets(tuple);
            TransitionTable transitionTable = myJsonParser.extractDelta(tuple);
            HashSet<State> finalStates = myJsonParser.extractFinalStates(tuple);
            State q1 = myJsonParser.extractStartState(tuple);
            DeterministicFiniteAutomataMachine dfa = dfaGenerator.generate(states, alphabets, transitionTable, q1, finalStates);

            System.out.println("Running test for " + stringStringHashMap.get("name"));
            runAllTestPassCases(stringStringHashMap, dfa, myJsonParser.extractTestCases(next).get("pass-cases"));
            runAllTestFailsCases(stringStringHashMap, dfa, myJsonParser.extractTestCases(next).get("fail-cases"));
        }

    }

    private void runAllTestFailsCases(HashMap<String, String> stringStringHashMap, DeterministicFiniteAutomataMachine dfa, ArrayList<String> strings) throws InvalidAlphabetException {
        System.out.println("<===== For fail cases : =====>");
        for (String string : strings) {
            boolean validate = dfa.validate(string);
            if (string.isEmpty()) string = "Empty";
            if (validate) {
                System.out.println(string + " is not valid");
            } else if (validate == false) {
                System.out.println(string + " is valid");
            }
        }

    }

    private void runAllTestPassCases(HashMap<String, String> stringStringHashMap, DeterministicFiniteAutomataMachine dfa, ArrayList<String> strings) throws InvalidAlphabetException {
        System.out.println("<===== For pass cases : =====>");
        for (String string : strings) {
            boolean validate = dfa.validate(string);
            if (string.isEmpty()) string = "Empty";

            if (validate) {
                System.out.println(string + " is valid");
            } else if (validate == false) {
                System.out.println(string + " is not valid");
            }
        }

    }
}
