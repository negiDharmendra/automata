package com.dharmenn.dfa;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransitionTableTest {
    @Test
    public void shouldGiveNextStateForGivenStateAndAlphabet() {
        State q0 = new State("q0");
        State q1 = new State("q1");

        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q0,"0",q0);
        transitionTable.addTransition(q0,"1",q1);
        transitionTable.addTransition(q1,"0",q0);
        transitionTable.addTransition(q1,"1",q1);

        assertEquals(q0,transitionTable.nextState(q0, "0"));
        assertEquals(q1,transitionTable.nextState(q0, "1"));

    }
}