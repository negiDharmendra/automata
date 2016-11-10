package com.tw.step.automata.nfa;

import com.tw.step.automata.util.State;
import com.tw.step.automata.util.States;
import com.tw.step.automata.util.TransitionTable;

import java.util.HashMap;
import java.util.HashSet;

public class NFATransitionTable implements TransitionTable<States> {
    private HashMap<State, HashMap<String, States>> transitionTable = new HashMap<>();
    private States prevStates = new States();

    public void addTransition(State inputTransition, String alphabet, States outputTransition) {
        if (transitionTable.containsKey(inputTransition))
            addTransitionForExistingState(inputTransition, alphabet, outputTransition);
        else
            addNewTransition(inputTransition, alphabet, outputTransition);

    }

    private void addNewTransition(State inputTransition, String alphabet, States outputTransition) {
        HashMap<String, States> alphabetToTransition = new HashMap<>();
        alphabetToTransition.put(alphabet, outputTransition);
        transitionTable.put(inputTransition, alphabetToTransition);
    }

    private void addTransitionForExistingState(State inputTransition, String alphabet, States outputTransition) {
        transitionTable.get(inputTransition).put(alphabet, outputTransition);
    }

    public States nextState(State currentState, String alphabet) {
        HashMap<String, States> stringHashSetHashMap = transitionTable.get(currentState);
        if (stringHashSetHashMap == null)
            return new States();
        States states = stringHashSetHashMap.get(alphabet);
        return states == null ? new States() : states;
    }

    private States getEpsilonStates(State previousState) {
        prevStates.add(previousState);
        States allEpsilonStates = new States();
        allEpsilonStates.add(previousState);
        HashMap<String, States> currentStates = transitionTable.getOrDefault(previousState, new HashMap<>());
        States currentEpsilonStates = currentStates.getOrDefault("e", new States());
        currentEpsilonStates.stream().filter(state -> !prevStates.contains(state)).forEach(state -> {
            States epsilonStates = getEpsilonStates(state);
            if (!epsilonStates.isEmpty())
                allEpsilonStates.addAll(epsilonStates);
        });
        return allEpsilonStates;
    }

    States getEpsilonStates(States states) {
        States allEpsilonStates = new States();
        states.forEach(state -> allEpsilonStates.addAll(getEpsilonStates(state)));
        return allEpsilonStates;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + transitionTable;
    }
}
