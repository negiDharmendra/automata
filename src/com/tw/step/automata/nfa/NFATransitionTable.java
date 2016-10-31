package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;

import java.util.HashMap;
import java.util.HashSet;

public class NFATransitionTable {
    private HashMap<State, HashMap<String, HashSet<State>>> transitionTable = new HashMap<>();

    void addTransition(State inputTransition, String alphabet, HashSet<State> outputTransition) {
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

    private void addTransitionForExistingState(State inputTransition, String alphabet, HashSet<State> outputTransition) {
        transitionTable.get(inputTransition).put(alphabet, outputTransition);
    }

    HashSet<State> nextStates(State initialState, String alphabet) {
        HashSet<State> nextStates = new HashSet<>();
        HashMap<String, HashSet<State>> transitionForCurrentState = transitionTable.get(initialState);
        HashSet<State> epsilonStates = transitionForCurrentState.get("e");
        if (epsilonStates == null) {
            return transitionForCurrentState.get(alphabet);
        } else {
            epsilonStates.add(initialState);
            transitionForEpsilonStates(epsilonStates,alphabet, nextStates);
        }
        return nextStates;
    }


    private void transitionForEpsilonStates(HashSet<State> epsilonStates, String alphabet, HashSet<State> nextStates) {
        epsilonStates.forEach((epsilonState) -> {
            HashMap<String, HashSet<State>> hashMap = transitionTable.get(epsilonState);
            if (hashMap != null && hashMap.get(alphabet) != null)
                nextStates.addAll(hashMap.get(alphabet));
        });
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + transitionTable;
    }
}
