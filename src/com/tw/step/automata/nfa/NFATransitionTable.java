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

    HashSet<State> nextStates(State currentState, String alphabet) {
        HashMap<String, HashSet<State>> stringHashSetHashMap = transitionTable.get(currentState);
        if (stringHashSetHashMap == null)
            return null;
        return stringHashSetHashMap.get(alphabet);
    }

    HashSet<State> getEpsilonStates(State q1) {
        HashSet<State> objects = new HashSet<>();
        objects.add(q1);
        HashMap<String, HashSet<State>> stringHashSetHashMap1 = transitionTable.get(q1);
        if (stringHashSetHashMap1 != null) {
            HashSet<State> stringHashSetHashMap = stringHashSetHashMap1.get("e");
            if (stringHashSetHashMap != null) {
                for (State state : stringHashSetHashMap) {
                    HashSet<State> epsilonStates = getEpsilonStates(state);
                    if (epsilonStates == null) {
                        break;
                    }
                    objects.addAll(epsilonStates);
                }
            }
        }
        return objects;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + transitionTable;
    }
}
