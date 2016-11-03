package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;
import com.tw.step.automata.util.TransitionTable;

import java.util.HashMap;
import java.util.HashSet;

class NFATransitionTable implements TransitionTable<HashSet<State>> {
    private HashMap<State, HashMap<String, HashSet<State>>> transitionTable = new HashMap<>();


    public void addTransition(State inputTransition, String alphabet, HashSet<State> outputTransition) {
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

    public HashSet<State> nextState(State currentState, String alphabet) {
        HashMap<String, HashSet<State>> stringHashSetHashMap = transitionTable.get(currentState);
        if (stringHashSetHashMap == null)
            return new HashSet<>();
        HashSet<State> states = stringHashSetHashMap.get(alphabet);
        return states == null ? new HashSet<>() : states;
    }

    private HashSet<State> getEpsilonStates(State previousState) {
        HashSet<State> allEpsilonStates = new HashSet<>();
        allEpsilonStates.add(previousState);
        HashMap<String, HashSet<State>> currentStates = transitionTable.get(previousState);
        if (currentStates != null) {
            HashSet<State> currentEpsilonStates = currentStates.get("e");
            if (currentEpsilonStates != null) {
                for (State state : currentEpsilonStates) {
                    HashSet<State> epsilonStates = getEpsilonStates(state);
                    if (epsilonStates.isEmpty())
                        break;
                    allEpsilonStates.addAll(epsilonStates);
                }
            }
        }
        return allEpsilonStates;
    }

    HashSet<State> getEpsilonStates(HashSet<State> states) {
        HashSet<State> allEpsilonStates = new HashSet<>();
        states.forEach(state -> allEpsilonStates.addAll(getEpsilonStates(state)));
        return allEpsilonStates;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + transitionTable;
    }
}
