package com.tw.step.automata.dfa;
import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.State;
import com.tw.step.automata.util.States;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFAMachineTest {

    private DFAMachine DFAMachine;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        State q0 = new State("q0");
        State q1 = new State("q1");

        DFATransitionTable DFATransitionTable = new DFATransitionTable();
        DFATransitionTable.addTransition(q0, "0", q0);
        DFATransitionTable.addTransition(q1, "0", q0);
        DFATransitionTable.addTransition(q0, "1", q1);
        DFATransitionTable.addTransition(q1, "1", q1);

        States states = new States();
        states.add(q0);
        states.add(q1);

        States finalStates = new States();
        finalStates.add(q1);

        HashSet<String> alphabet = new HashSet<>();
        alphabet.add("0");
        alphabet.add("1");

        DFAMachine = new DFAMachine(states, alphabet, DFATransitionTable, q0, finalStates);

    }

    @Test
    public void shouldAcceptValidString() throws InvalidAlphabetException {


        assertTrue(DFAMachine.validate("01"));
        assertTrue(DFAMachine.validate("1"));
        assertTrue(DFAMachine.validate("01010101111000001"));

    }

    @Test
    public void shouldNotAcceptInvalidString() throws InvalidAlphabetException {

        assertFalse(DFAMachine.validate("0"));
        assertFalse(DFAMachine.validate("010101011110"));

    }

    @Test
    public void shouldThrowInvalidAlphabetExceptionForInvalidAlphabet() throws InvalidAlphabetException {
        thrown.expect(InvalidAlphabetException.class);
        thrown.expectMessage("2 is not a valid alphabet");
        DFAMachine.validate("012");

    }
}