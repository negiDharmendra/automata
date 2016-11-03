package com.tw.step.automata.dfa;
import com.tw.step.automata.util.FiniteAutomataMachine;
import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.State;
import com.tw.step.automata.util.TransitionTable;

import java.util.HashSet;

class DFAMachine implements FiniteAutomataMachine {
    private final HashSet<State> states;
    private final HashSet<String> alphabets;
    private final DFATransitionTable dfaTransitionTable;
    private final State initialState;
    private final HashSet<State> finalStates;

    DFAMachine(HashSet<State> states, HashSet<String> alphabets, TransitionTable dfaTransitionTable, State initialState, HashSet<State> finalStates) {
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
