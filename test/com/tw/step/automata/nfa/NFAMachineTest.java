package com.tw.step.automata.nfa;

import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.State;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFAMachineTest {

    private NFAMachine nfaMachine;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");

        NFATransitionTable nfaTransitionTable = new NFATransitionTable();

        HashSet<State> stateSet1 = new HashSet<>();
        stateSet1.add(q1);
        nfaTransitionTable.addTransition(q1, "a", stateSet1);

        HashSet<State> stateSet2 = new HashSet<>();
        stateSet2.add(q2);
        nfaTransitionTable.addTransition(q1, "b", stateSet2);


        HashSet<State> stateSet3 = new HashSet<>();
        stateSet3.add(q2);
        nfaTransitionTable.addTransition(q2, "a", stateSet3);

        HashSet<State> stateSet4 = new HashSet<>();
        stateSet4.add(q3);
        nfaTransitionTable.addTransition(q2, "b", stateSet4);

        HashSet<State> stateSet5 = new HashSet<>();
        stateSet5.add(q1);
        nfaTransitionTable.addTransition(q3, "a", stateSet5);

        HashSet<State> stateSet6 = new HashSet<>();
        stateSet6.add(q3);
        nfaTransitionTable.addTransition(q1, "e", stateSet6);

        HashSet<State> stateSet7 = new HashSet<>();
        stateSet7.add(q1);
        nfaTransitionTable.addTransition(q1, "", stateSet7);

        HashSet<String> alphabets = new HashSet<>();
        alphabets.add("a");
        alphabets.add("b");
        alphabets.add("");

        HashSet<State> allStates = new HashSet<>();
        allStates.add(q1);
        allStates.add(q2);
        allStates.add(q3);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q1);

        nfaMachine = new NFAMachine(allStates, alphabets, nfaTransitionTable, q1, finalStates);

    }

    @Test
    public void shouldValidateAValidString() throws InvalidAlphabetException {
        assertTrue(nfaMachine.validate(""));
        assertTrue(nfaMachine.validate("a"));
        assertTrue(nfaMachine.validate("aa"));
        assertTrue(nfaMachine.validate("bba"));
        assertTrue(nfaMachine.validate("bbaa"));
        assertTrue(nfaMachine.validate("bbabba"));
        assertTrue(nfaMachine.validate("baba"));
    }

    @Test
    public void shouldValidateAInvalidString() throws InvalidAlphabetException {
        assertFalse(nfaMachine.validate("b"));
        assertFalse(nfaMachine.validate("ab"));
        assertFalse(nfaMachine.validate("bbba"));
        assertFalse(nfaMachine.validate("baa"));
    }


    @Test
    public void shouldThrowInvalidAlphabetExceptionForInvalidAlphabet() throws InvalidAlphabetException {
        thrown.expect(InvalidAlphabetException.class);
        thrown.expectMessage("c is not a valid alphabet");
        nfaMachine.validate("ac");

    }
}