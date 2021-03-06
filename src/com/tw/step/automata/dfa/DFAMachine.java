package com.tw.step.automata.dfa;
import com.tw.step.automata.util.*;

import java.util.HashSet;

public class DFAMachine implements FiniteAutomataMachine {
    private final States states;
    private final HashSet<String> alphabets;
    private final DFATransitionTable dfaTransitionTable;
    private final State initialState;
    private final States finalStates;

    public DFAMachine(States states, HashSet<String> alphabets, TransitionTable dfaTransitionTable, State initialState, States finalStates) {
        this.states = states;
        this.alphabets = alphabets;
        this.dfaTransitionTable = (DFATransitionTable)dfaTransitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean validate(String alphabets) throws InvalidAlphabetException {
        String[] split = alphabets.split("");
        State state = initialState;
        for (String s : split) {
            validateAlphabet(s);
            state = dfaTransitionTable.nextState(state, s);
        }
        return finalStates.contains(state);
    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if (!alphabet.isEmpty() && !alphabets.contains(alphabet)) {
            throw new InvalidAlphabetException(alphabet);
        }
    }
}
