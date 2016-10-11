import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

import static org.junit.Assert.*;

public class DeterministicFiniteAutometaMachineTest {

    private DeterministicFiniteAutometaMachine deterministicFiniteAutometaMachine;

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

        deterministicFiniteAutometaMachine = new DeterministicFiniteAutometaMachine(states, alphabet, transitionTable, q0, finalStates);

    }

    @Test
    public void shouldAcceptValidString() throws InvalidAlphabetException {


        assertTrue(deterministicFiniteAutometaMachine.validate("01"));
        assertTrue(deterministicFiniteAutometaMachine.validate("1"));
        assertTrue(deterministicFiniteAutometaMachine.validate("01010101111000001"));

    }

    @Test
    public void shouldNotAcceptInvalidString() throws InvalidAlphabetException {

        assertFalse(deterministicFiniteAutometaMachine.validate("0"));
        assertFalse(deterministicFiniteAutometaMachine.validate("010101011110"));

    }

    @Test
    public void shouldThrowInvalidAlphabetExceptionForInvalidAlphabet() throws InvalidAlphabetException {
        thrown.expect(InvalidAlphabetException.class);
        thrown.expectMessage("2 is not a valid alphabet");
        deterministicFiniteAutometaMachine.validate("012");

    }
}