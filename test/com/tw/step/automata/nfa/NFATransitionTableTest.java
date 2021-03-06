package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;
import com.tw.step.automata.util.States;
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

        States states = new States();

        states.add(q2);
        states.add(q4);
        nfaTransitionTable.addTransition(q1, "e", states);

        States states1 = new States();
        states1.add(q3);
        nfaTransitionTable.addTransition(q2, "e", states1);


        States states2 = new States();
        states2.add(q2);
        nfaTransitionTable.addTransition(q2, "0", states2);


        States states3 = new States();
        states3.add(q3);
        nfaTransitionTable.addTransition(q3, "1", states3);

        States states4 = new States();
        states4.add(q6);
        nfaTransitionTable.addTransition(q3, "e", states4);

        States states5 = new States();
        states5.add(q4);
        nfaTransitionTable.addTransition(q4, "1", states5);

        States states6 = new States();
        states6.add(q5);
        nfaTransitionTable.addTransition(q4, "e", states6);


        States states7 = new States();
        states7.add(q5);
        nfaTransitionTable.addTransition(q5, "0", states7);

        States states8 = new States();
        states8.add(q7);
        nfaTransitionTable.addTransition(q5, "e", states8);

    }

    @Test
    public void shouldGiveNextSetOfStateForAGivenStateAndAlphabet() {

        States expectedStates = new States();
        expectedStates.add(q4);

        States expectedStates1 = new States();
        expectedStates1.add(q2);

        assertEquals(expectedStates, nfaTransitionTable.nextState(q4, "1"));
        assertEquals(expectedStates1, nfaTransitionTable.nextState(q2, "0"));
    }

    @Test
    public void shouldReturnNullForInvalidTransition() {

        assertEquals(new States(), nfaTransitionTable.nextState(q1, "1"));
        assertEquals(new States(), nfaTransitionTable.nextState(q4, "0"));
    }

    @Test
    public void shouldReturnAllEpsilonStatesForAGivenSetOfState() {
        States states = new States();
        states.add(q3);
        states.add(q5);

        States expectedStates2 = new States();
        expectedStates2.add(q6);
        expectedStates2.add(q7);
        expectedStates2.addAll(states);

        assertEquals(expectedStates2, nfaTransitionTable.getEpsilonStates(states));
    }


    @Test
    public void shouldResolveRecursiveEpsilonLoop() {
        NFATransitionTable nfaTransitionTable = new NFATransitionTable();
        States states1 = new States();
        states1.add(q2);
        states1.add(q3);

        States states2 = new States();
        states2.add(q1);

        nfaTransitionTable.addTransition(q1,"e",states1);
        nfaTransitionTable.addTransition(q2,"e",states2);

        States states = new States();
        states.add(q1);

        States expectedStates2 = new States();
        expectedStates2.add(q2);
        expectedStates2.add(q3);
        expectedStates2.addAll(states);

        assertEquals(expectedStates2, nfaTransitionTable.getEpsilonStates(states));
    }
}