package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class NFAGeneratorTest {

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

        NFATransitionTable nfaTransitionTable = new NFATransitionTable();

        HashSet<State> states2 = new HashSet<>();
        states2.add(q2);
        nfaTransitionTable.addTransition(q2, "0", states2);


        HashSet<State> states3 = new HashSet<>();
        states3.add(q3);
        nfaTransitionTable.addTransition(q3, "1", states3);


        HashSet<State> states5 = new HashSet<>();
        states5.add(q1);
        nfaTransitionTable.addTransition(q1, "1", states5);



        HashSet<State> states7 = new HashSet<>();
        states7.add(q3);
        nfaTransitionTable.addTransition(q3, "0", states7);


        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q1);

        NFAGenerator dfaGenerator = new NFAGenerator();
        NFAMachine generate = (NFAMachine) dfaGenerator.generate(states, alphabets, nfaTransitionTable, q1, finalStates);

        assertEquals("com.tw.step.automata.nfa.NFAMachine", generate.getClass().getName());
    }

}