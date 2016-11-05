package com.tw.step.automata.testRunner;

import com.tw.step.automata.dfa.DFATransitionTable;
import com.tw.step.automata.nfa.NFATransitionTable;
import com.tw.step.automata.util.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class DFATestRunner {

    private final HashMap<String, FiniteAutomataGenerator> faMachineGenerators;
    private final JsonToFAComponentParser jsonToFAComponentParser;

    DFATestRunner(HashMap<String, FiniteAutomataGenerator> faMachineGenerators, JsonToFAComponentParser jsonToFAComponentParser) {

        this.faMachineGenerators = faMachineGenerators;
        this.jsonToFAComponentParser = jsonToFAComponentParser;
    }

    boolean runAll() throws InvalidAlphabetException {
        HashMap<String, TransitionTable> transitionTables = new HashMap<>();
        transitionTables.put("dfa", new DFATransitionTable());
        transitionTables.put("nfa", new NFATransitionTable());

        HashSet<String> alphabets = jsonToFAComponentParser.parseAlphabets();
        TransitionTable transitionTable = jsonToFAComponentParser.parseTransitionFunction(transitionTables);
        HashMap<String, String> machineInfo = jsonToFAComponentParser.parseMachineInfo();
        States finalStates = jsonToFAComponentParser.parseFinalStates();
        State initialState = jsonToFAComponentParser.parseInitialStates();
        States states = jsonToFAComponentParser.parseAllStates();

        FiniteAutomataGenerator faGenerator = faMachineGenerators.get(machineInfo.get(FAutomata.TYPE.value()));
        FiniteAutomataMachine machine = faGenerator.generate(states, alphabets, transitionTable, initialState, finalStates);

        boolean passCases = isPassCases(machine, FAutomata.PASSCASES.value(), false);
        boolean failCases = isPassCases(machine, FAutomata.FAILCASES.value(), true);
        return passCases && !failCases;
    }

    private boolean isPassCases(FiniteAutomataMachine finiteAutomataMachine, String testCaseType, boolean defaultResult) throws InvalidAlphabetException {
        HashMap<String, List<String>> testCases = jsonToFAComponentParser.parseTestCases();
        for (String testCase : testCases.get(testCaseType))
            defaultResult = finiteAutomataMachine.validate(testCase);
        return defaultResult;
    }
}