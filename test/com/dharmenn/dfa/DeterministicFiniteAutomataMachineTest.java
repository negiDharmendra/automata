package com.dharmenn.dfa;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeterministicFiniteAutomataMachineTest {

    private DeterministicFiniteAutomataMachine deterministicFiniteAutomataMachine;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        State q0 = new State("q0");
        State q1 = new State("q1");

        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q0, "0", q0);
        transitionTable.addTransition(q1, "0", q0);
        transitionTable.addTransition(q0, "1", q1);
        transitionTable.addTransition(q1, "1", q1);

        HashSet<State> states = new HashSet<>();
        states.add(q0);
        states.add(q1);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q1);

        HashSet<String> alphabet = new HashSet<>();
        alphabet.add("0");
        alphabet.add("1");

        deterministicFiniteAutomataMachine = new DeterministicFiniteAutomataMachine(states, alphabet, transitionTable, q0, finalStates);

    }

    @Test
    public void shouldAcceptValidString() throws InvalidAlphabetException {


        assertTrue(deterministicFiniteAutomataMachine.validate("01"));
        assertTrue(deterministicFiniteAutomataMachine.validate("1"));
        assertTrue(deterministicFiniteAutomataMachine.validate("01010101111000001"));

    }

    @Test
    public void shouldNotAcceptInvalidString() throws InvalidAlphabetException {

        assertFalse(deterministicFiniteAutomataMachine.validate("0"));
        assertFalse(deterministicFiniteAutomataMachine.validate("010101011110"));

    }

    @Test
    public void shouldThrowInvalidAlphabetExceptionForInvalidAlphabet() throws InvalidAlphabetException {
        thrown.expect(InvalidAlphabetException.class);
        thrown.expectMessage("2 is not a valid alphabet");
        deterministicFiniteAutomataMachine.validate("012");

    }
}