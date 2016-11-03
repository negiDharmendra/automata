package com.tw.step.automata.dfa;

import com.tw.step.automata.util.State;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class DFAGeneratorTest {
    @Test
    public void shouldGenerateADFAForAGivenTuple() throws Exception {
        HashSet<State> states = new HashSet<>();
        State q1 = new State("q1");
        states.add(q1);
        State q2 = new State("q2");
        states.add(q2);
        State q3 = new State("q3");
        states.add(q3);

        HashSet<String> alphabets = new HashSet<>();
        alphabets.add("1");
        alphabets.add("0");

        DFATransitionTable DFATransitionTable = new DFATransitionTable();
        DFATransitionTable.addTransition(q1, "0", q2);
        DFATransitionTable.addTransition(q1, "1", q2);
        DFATransitionTable.addTransition(q2, "0", q3);
        DFATransitionTable.addTransition(q2, "1", q3);
        DFATransitionTable.addTransition(q3, "0", q1);
        DFATransitionTable.addTransition(q3, "1", q1);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q1);

        DFAGenerator dfaGenerator = new DFAGenerator();
        DFAMachine generate = (DFAMachine) dfaGenerator.generate(states, alphabets, DFATransitionTable, q1, finalStates);

        assertEquals("com.tw.step.automata.dfa.DFAMachine", generate.getClass().getName());
    }
}