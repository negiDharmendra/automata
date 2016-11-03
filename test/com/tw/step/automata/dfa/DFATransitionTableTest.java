package com.tw.step.automata.dfa;
import com.tw.step.automata.util.State;
import org.junit.Test;

import static org.junit.Assert.*;

public class DFATransitionTableTest {
    @Test
    public void shouldGiveNextStateForGivenStateAndAlphabet() {
        State q0 = new State("q0");
        State q1 = new State("q1");

        DFATransitionTable DFATransitionTable = new DFATransitionTable();
        DFATransitionTable.addTransition(q0,"0",q0);
        DFATransitionTable.addTransition(q0,"1",q1);
        DFATransitionTable.addTransition(q1,"0",q0);
        DFATransitionTable.addTransition(q1,"1",q1);

        assertEquals(q0, DFATransitionTable.nextState(q0, "0"));
        assertEquals(q1, DFATransitionTable.nextState(q0, "1"));

    }
}