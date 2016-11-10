package com.tw.step.automata.testRunner;

import com.tw.step.automata.dfa.DFAGenerator;
import com.tw.step.automata.nfa.NFAGenerator;
import com.tw.step.automata.util.FiniteAutomataGenerator;
import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.JsonToFAComponentParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws InvalidAlphabetException, IOException {
        String jsonText = Files.readAllLines(Paths.get("examples1.json")).get(0);
        System.out.println(jsonText);
        jsonText = jsonText.replaceAll("\\\\|\"", "");
        JSONArray objects = new JSONArray(jsonText);
        HashMap<String, FiniteAutomataGenerator> finiteAutomataGenerators = new HashMap<>();
        finiteAutomataGenerators.put("dfa", new DFAGenerator());
        finiteAutomataGenerators.put("nfa", new NFAGenerator());

        for (Object object : objects) {
            JsonToFAComponentParser jsonToFAComponentParser = new JsonToFAComponentParser(object.toString());
            new DFATestRunner(finiteAutomataGenerators, jsonToFAComponentParser).runAll();
        }
    }

}
