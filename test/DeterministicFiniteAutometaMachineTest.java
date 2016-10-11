import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class DeterministicFiniteAutometaMachineTest {

    private TransitionTable transitionTable;
    private HashSet<State> states;
    private HashSet<State> finalStates;
    private HashSet<String> alphabet;
    private State q0;
    private State q1;

    @Before
    public void setUp() {
        q0 = new State("q0");
        q1 = new State("q1");

        transitionTable = new TransitionTable();
        transitionTable.addTransition(q0, "0", q0);
        transitionTable.addTransition(q1, "0", q0);
        transitionTable.addTransition(q0, "1", q1);
        transitionTable.addTransition(q1, "1", q1);

        states = new HashSet<>();
        states.add(q0);
        states.add(q1);

        finalStates = new HashSet<>();
        finalStates.add(q1);

        alphabet = new HashSet<>();
        alphabet.add("0");
        alphabet.add("1");

    }

    @Test
    public void shouldAcceptValidString() throws InvalidAlphabetException {

        DeterministicFiniteAutometaMachine deterministicFiniteAutometaMachine = new DeterministicFiniteAutometaMachine(states, alphabet, transitionTable, q0, finalStates);

        assertTrue(deterministicFiniteAutometaMachine.validate("01"));
        assertTrue(deterministicFiniteAutometaMachine.validate("1"));
        assertTrue(deterministicFiniteAutometaMachine.validate("01010101111000001"));

    }

    @Test
    public void shouldNotAcceptInvalidString() throws InvalidAlphabetException {


        DeterministicFiniteAutometaMachine deterministicFiniteAutometaMachine = new DeterministicFiniteAutometaMachine(states, alphabet, transitionTable, q0, finalStates);

        assertFalse(deterministicFiniteAutometaMachine.validate("0"));
        assertFalse(deterministicFiniteAutometaMachine.validate("010101011110"));

    }
}