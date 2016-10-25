package com.tw.step.nfa;

import com.tw.step.dfa.State;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class NFATransitionTableTest {
    @Test
    public void shouldGiveNextSetOfStateForAGivenStateAndAlphabet() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        NFATransitionTable nfaTransitionTable = new NFATransitionTable();

        HashSet<State> states = new HashSet<>();

        states.clear();
        states.add(q1);
        nfaTransitionTable.addTransition(q1,"a",states);

        HashSet<State> states1 = new HashSet<>();
        states1.add(q2);
        nfaTransitionTable.addTransition(q2,"a",states1);

        HashSet<State> states2 = new HashSet<>();
        states2.add(q3);
        nfaTransitionTable.addTransition(q2,"b",states2);

        HashSet<State> states3 = new HashSet<>();
        states3.add(q1);
        nfaTransitionTable.addTransition(q3,"a",states3);


        HashSet<State> expectedStates = new HashSet<>();
        expectedStates.add(q1);

        assertEquals(expectedStates, nfaTransitionTable.nextStates(q1,"a"));
    }


    @Test
    public void shouldApplyTransitionForAllStateConnectedByEpsilon() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");
        State q5 = new State("q5");
        State q6 = new State("q6");
        State q7 = new State("q7");
        State q8 = new State("q8");
        State q9 = new State("q9");

        NFATransitionTable nfaTransitionTable = new NFATransitionTable();

        HashSet<State> states = new HashSet<>();

        states.clear();
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);
        states.add(q5);
        states.add(q7);
        states.add(q9);
        nfaTransitionTable.addTransition(q1,"e",states);

        HashSet<State> states1 = new HashSet<>();
        states1.add(q6);
        nfaTransitionTable.addTransition(q5,"a",states1);


        HashSet<State> states2 = new HashSet<>();
        states2.add(q5);
        nfaTransitionTable.addTransition(q6,"e",states2);

        HashSet<State> expectedStates = new HashSet<>();
        expectedStates.add(q6);
        expectedStates.add(q5);

        assertEquals(expectedStates, nfaTransitionTable.nextStates(q1,"a"));
    }
}