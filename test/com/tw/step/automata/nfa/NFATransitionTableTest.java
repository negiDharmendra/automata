package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class NFATransitionTableTest {

    private NFATransitionTable nfaTransitionTable;
    private State q1;
    private State q2;
    private State q3;
    private State q4;
    private State q5;
    private State q6;
    private State q7;

    @Before
    public void setUp() throws Exception {
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        q4 = new State("q4");
        q5 = new State("q5");
        q6 = new State("q6");
        q7 = new State("q7");

        nfaTransitionTable = new NFATransitionTable();

        HashSet<State> states = new HashSet<>();

        states.add(q2);
        states.add(q4);
        nfaTransitionTable.addTransition(q1, "e", states);

        HashSet<State> states1 = new HashSet<>();
        states1.add(q3);
        nfaTransitionTable.addTransition(q2, "e", states1);


        HashSet<State> states2 = new HashSet<>();
        states2.add(q2);
        nfaTransitionTable.addTransition(q2, "0", states2);


        HashSet<State> states3 = new HashSet<>();
        states3.add(q3);
        nfaTransitionTable.addTransition(q3, "1", states3);

        HashSet<State> states4 = new HashSet<>();
        states4.add(q6);
        nfaTransitionTable.addTransition(q3, "e", states4);

        HashSet<State> states5 = new HashSet<>();
        states5.add(q4);
        nfaTransitionTable.addTransition(q4, "1", states5);

        HashSet<State> states6 = new HashSet<>();
        states6.add(q5);
        nfaTransitionTable.addTransition(q4, "e", states6);


        HashSet<State> states7 = new HashSet<>();
        states7.add(q5);
        nfaTransitionTable.addTransition(q5, "0", states7);

        HashSet<State> states8 = new HashSet<>();
        states8.add(q7);
        nfaTransitionTable.addTransition(q5, "e", states8);

    }

    @Test
    public void shouldGiveNextSetOfStateForAGivenStateAndAlphabet() {

        HashSet<State> expectedStates = new HashSet<>();
        expectedStates.add(q4);

        HashSet<State> expectedStates1 = new HashSet<>();
        expectedStates1.add(q2);

        assertEquals(expectedStates, nfaTransitionTable.nextStates(q4, "1"));
        assertEquals(expectedStates1, nfaTransitionTable.nextStates(q2, "0"));
    }

    @Test
    public void shouldNullForInvalidTransition() {

        assertEquals(null, nfaTransitionTable.nextStates(q1, "1"));
        assertEquals(null, nfaTransitionTable.nextStates(q4, "0"));
    }

    @Test
    public void shouldReturnAllEpsilonStatesForAGivenState() {

        HashSet<State> expectedStates2 = new HashSet<>();
        expectedStates2.add(q1);
        expectedStates2.add(q2);
        expectedStates2.add(q3);
        expectedStates2.add(q4);
        expectedStates2.add(q5);
        expectedStates2.add(q6);
        expectedStates2.add(q7);

        assertEquals(expectedStates2, nfaTransitionTable.getEpsilonStates(q1));


    }
}