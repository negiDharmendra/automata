import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class DeterministicFiniteAutometaMachineTest {
    @Test
    public void dfaMachineAcceptValidString() throws InvalidAlphabetException {
        State q0 = new State("q0");
        State q1 = new State("q1");

        TransitionTable transitionTable = new TransitionTable();
        transitionTable.addTransition(q0, "0",q0);
        transitionTable.addTransition(q1, "0",q0);
        transitionTable.addTransition(q0, "1",q1);
        transitionTable.addTransition(q1, "1",q1);

        HashSet<State> states = new HashSet<>();
        states.add(q0);
        states.add(q1);

        HashSet<State> finalStates = new HashSet<>();
        finalStates.add(q1);

        HashSet<String> alphabet = new HashSet<>();
        alphabet.add("0");
        alphabet.add("1");

        DeterministicFiniteAutometaMachine deterministicFiniteAutometaMachine = new DeterministicFiniteAutometaMachine(states, alphabet, transitionTable, q0, finalStates);
        assertTrue(deterministicFiniteAutometaMachine.validate("01"));
        assertTrue(deterministicFiniteAutometaMachine.validate("1"));
        assertFalse(deterministicFiniteAutometaMachine.validate("0"));


    }
}