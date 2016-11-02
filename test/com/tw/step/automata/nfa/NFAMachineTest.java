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
        State q4 = new State("q4");
        State q5 = new State("q5");
        State q6 = new State("q6");
        State q7 = new State("q7");

       NFATransitionTable nfaTransitionTable = new NFATransitionTable();

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

        HashSet<String> alphabets = new HashSet<>();
        alphabets.add("1");
        alphabets.add("0");

        HashSet<State> allStates = new HashSet<>();
        allStates.add(q1);
        allStates.add(q2);
        allStates.add(q3);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q6);
        finalStates.add(q7);

        nfaMachine = new NFAMachine(allStates, alphabets, nfaTransitionTable, q1, finalStates);

    }

    @Test
    public void shouldValidateAValidString() throws InvalidAlphabetException {
        assertTrue(nfaMachine.validate(""));
        assertTrue(nfaMachine.validate("1"));
        assertTrue(nfaMachine.validate("0"));
        assertTrue(nfaMachine.validate("0011"));
        assertTrue(nfaMachine.validate("1100"));
    }

    @Test
    public void shouldNotValidateAInvalidString() throws InvalidAlphabetException {
        assertFalse(nfaMachine.validate("101"));
        assertFalse(nfaMachine.validate("010"));
        assertFalse(nfaMachine.validate("11001"));
        assertFalse(nfaMachine.validate("00110"));
    }

    @Test
    public void shouldThrowInvalidAlphabetExceptionForInvalidAlphabet() throws InvalidAlphabetException {
        thrown.expect(InvalidAlphabetException.class);
        thrown.expectMessage("c is not a valid alphabet");
        nfaMachine.validate("c");

    }
}