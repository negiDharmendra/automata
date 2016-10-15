package com.dharmenn.dfa;
import java.util.HashSet;

class DeterministicFiniteAutometaMachine {
    private final HashSet<State> states;
    private final HashSet<String> alphabets;
    private final TransitionTable transitionTable;
    private final State initialState;
    private final HashSet<State> finalStates;

    DeterministicFiniteAutometaMachine(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State initialState, HashSet<State> finalStates) {
        this.states = states;
        this.alphabets = alphabets;
        this.transitionTable = transitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    boolean validate(String alphabets) throws InvalidAlphabetException {
        String[] split = alphabets.split("");
        State state = initialState;
        for (String s : split) {
            validateAlphabet(s);
            state = transitionTable.nextState(state, s);
        }
        return finalStates.contains(state);
    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if (!alphabets.contains(alphabet)) {
            throw new InvalidAlphabetException(alphabet);
        }
    }
}
