package com.tw.step.nfa;

import com.tw.step.dfa.State;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class NFATransitionTableTest {
    @Test
    public void shouldGiveNextSetOfStateForAGivenStateAndAlphabet() {
        State q1 = new State("q1");
        State q2 = new State("q1");
        State q3 = new State("q1");
        NFATransitionTable nfaTransitionTable = new NFATransitionTable();

        HashSet<State> states = new HashSet<>();
        states.add(q1);
        nfaTransitionTable.addTransition(q1,"a",states);

        states.clear();
        states.add(q2);
        nfaTransitionTable.addTransition(q1,"b",states);

        states.clear();
        states.add(q2);
        nfaTransitionTable.addTransition(q2,"a",states);

        states.clear();
        states.add(q3);
        nfaTransitionTable.addTransition(q2,"b",states);

        states.clear();
        states.add(q1);
        nfaTransitionTable.addTransition(q3,"a",states);

        nfaTransitionTable.addTransition(q3,"b",new HashSet<State>());


        HashSet<State> expectedStates = new HashSet<>();
        expectedStates.add(q1);

        assertEquals(expectedStates, nfaTransitionTable.nextStates(q1,"a"));
    }
}