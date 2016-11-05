package com.tw.step.automata.util;

import com.tw.step.automata.dfa.DFATransitionTable;
import com.tw.step.automata.nfa.NFATransitionTable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class JsonToFAComponentParserTest {

    private JsonToFAComponentParser jsonToFAComponentParser;

    @Before
    public void setUp() throws Exception {
        String jsonString = "{\"name\":\"odd number of zeroes\"," +
                "\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"]," +
                "\"alphabets\":[\"1\",\"0\"]," +
                "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
                "\"start-state\":\"q1\",\"final-states\":[\"q2\"]}," +
                "\"pass-cases\":[\"0\",\"000\"]," +
                "\"fail-cases\":[\"00\",\"0000\"]}";
        jsonToFAComponentParser = new JsonToFAComponentParser(jsonString);
    }

    @Test
    public void shouldParseAlphabets() throws Exception {
        HashSet<String> expected = new HashSet<>();
        expected.add("0");
        expected.add("1");
        assertEquals(expected, jsonToFAComponentParser.parseAlphabets());
    }

    @Test
    public void shouldParseMachineInfo() throws Exception {
        HashMap<String, String> expected = new HashMap<>();
        expected.put(FAutomata.NAME.value(), "odd number of zeroes");
        expected.put(FAutomata.TYPE.value(), FAutomata.DFA.value());
        assertEquals(expected, jsonToFAComponentParser.parseMachineInfo());
    }

    @Test
    public void shouldParseTestCases() throws Exception {
        HashMap<String, List<String>> expected = new HashMap<>();
        ArrayList<String> passCases = new ArrayList<>();
        passCases.add("0");
        passCases.add("000");
        expected.put(FAutomata.PASSCASES.value(), passCases);

        ArrayList<String> failCases = new ArrayList<>();
        failCases.add("00");
        failCases.add("0000");
        expected.put(FAutomata.FAILCASES.value(), failCases);

        assertEquals(expected, jsonToFAComponentParser.parseTestCases());
    }

    @Test
    public void shouldParseInitialState() throws Exception {
        assertEquals(new State("q1"), jsonToFAComponentParser.parseInitialStates());
    }

    @Test
    public void shouldParseFinalState() throws Exception {
        States expected = new States();
        expected.add(new State("q2"));
        assertEquals(expected, jsonToFAComponentParser.parseFinalStates());
    }

    @Test
    public void shouldParseAllStates() throws Exception {
        States expected = new States();
        expected.add(new State("q1"));
        expected.add(new State("q2"));
        assertEquals(expected, jsonToFAComponentParser.parseAllStates());
    }

    @Test
    public void shouldParseTransitionFunction() throws Exception {
        HashMap<String, TransitionTable> transitionTables = new HashMap<>();
        transitionTables.put(FAutomata.DFA.value(), new DFATransitionTable());

        DFATransitionTable expected = new DFATransitionTable();
        State q1 = new State("q1");
        State q2 = new State("q2");
        expected.addTransition(q1, "0", q2);
        expected.addTransition(q1, "1", q1);
        expected.addTransition(q2, "0", q1);
        expected.addTransition(q2, "1", q2);


        assertEquals(expected, jsonToFAComponentParser.parseTransitionFunction(transitionTables));
    }

}