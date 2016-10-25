package com.tw.step.nfa;

import com.tw.step.dfa.State;

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

    HashSet<State> nextStates(State initialState, String s) {
        HashSet<State> result = new HashSet<>();
        HashMap<String, HashSet<State>> stringHashSetHashMap = transitionTable.get(initialState);
        HashSet<State> e = stringHashSetHashMap.get("e");
        if (e == null) {
            return stringHashSetHashMap.get(s);
        } else {
            e.add(initialState);
            for (State state : e) {
                HashMap<String, HashSet<State>> hashMap = transitionTable.get(state);
                if (hashMap != null && hashMap.get(s)!=null)
                    result.addAll(hashMap.get(s));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getName() +" : "+ transitionTable;
    }
}
