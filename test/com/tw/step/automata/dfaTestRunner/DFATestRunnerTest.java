package com.tw.step.automata.dfaTestRunner;

import com.tw.step.automata.dfa.DFAGenerator;
import com.tw.step.automata.nfa.NFAGenerator;
import com.tw.step.automata.util.FiniteAutomataGenerator;
import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.JsonToFAComponentParser;
import net.minidev.json.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class DFATestRunnerTest {


    @Test
    public void shouldRunAllTestCasesForDFA() throws IOException, ParseException, InvalidAlphabetException {

        String jsonString = "{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";
        JsonToFAComponentParser jsonToFAComponentParser = new JsonToFAComponentParser(jsonString);

        HashMap<String, FiniteAutomataGenerator> finiteAutomataMachineGenerator = new HashMap<>();
        finiteAutomataMachineGenerator.put("dfa", new DFAGenerator());

        DFATestRunner dfaTestRunner = new DFATestRunner(finiteAutomataMachineGenerator, jsonToFAComponentParser);
        assertTrue(dfaTestRunner.runAll());

    }


    @Test
    public void shouldRunAllTestCasesForNFA() throws IOException, ParseException, InvalidAlphabetException {

        String jsonString = "{\"name\":\"0*1* or 1*0* with extra epsilons\",\"type\":\"nfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q7\",\"q2\",\"q5\",\"q6\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"e\":[\"q2\",\"q4\"]},\"q2\":{\"0\":[\"q2\"],\"e\":[\"q3\"]},\"q3\":{\"1\":[\"q3\"],\"e\":[\"q6\"]},\"q4\":{\"1\":[\"q4\"],\"e\":[\"q5\"]},\"q5\":{\"0\":[\"q5\"],\"e\":[\"q7\"]}},\"start-state\":\"q1\",\"final-states\":[\"q7\",\"q6\"]},\"pass-cases\":[\"\",\"0\",\"1\",\"00\",\"11\",\"001\",\"110\",\"011\",\"100\",\"0011\",\"1100\"],\"fail-cases\":[\"101\",\"010\",\"11001\",\"00110\",\"0101\",\"1010\"]}";
        JsonToFAComponentParser jsonToFAComponentParser = new JsonToFAComponentParser(jsonString);

        HashMap<String, FiniteAutomataGenerator> finiteAutomataMachineGenerator = new HashMap<>();
        finiteAutomataMachineGenerator.put("nfa", new NFAGenerator());

        DFATestRunner dfaTestRunner = new DFATestRunner(finiteAutomataMachineGenerator, jsonToFAComponentParser);
        assertTrue(dfaTestRunner.runAll());

    }
}