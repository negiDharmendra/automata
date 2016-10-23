package com.tw.step.nfa;

import com.tw.step.dfa.State;

import java.util.HashMap;
import java.util.HashSet;

public class NFATransitionTable {
    private HashMap<State, HashMap<String, HashSet<State>>> transitionTable = new HashMap<>();

    void addTransition(State inputTransition, String alphabet, HashSet outputTransition) {
        if (transitionTable.containsKey(inputTransition))
            addTransitionForExistingState(inputTransition, alphabet, outputTransition);
        else
            addNewTransition(inputTransition, alphabet, outputTransition);

    }

    private void addNewTransition(State inputTransition, String alphabet, HashSet<State> outputTransition) {
        HashMap<String, HashSet<State>> alphabetToTransition = new HashMap<>();
        alphabetToTransition.put(alphabet, outputTransition);
        transitionTable.put(inputTransition, alphabetToTransition);
    }

    private void addTransitionForExistingState(State inputTransition, String alphabet, HashSet outputTransition) {
        transitionTable.get(inputTransition).put(alphabet, outputTransition);
    }

    HashSet<State> nextStates(State initialState, String s) {
        return transitionTable.get(initialState).get(s);
    }
}
