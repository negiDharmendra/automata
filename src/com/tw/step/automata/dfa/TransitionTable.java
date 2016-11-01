package com.tw.step.automata.dfa;
import com.tw.step.automata.util.State;

import java.util.HashMap;

public class TransitionTable {
    private HashMap<State, HashMap<String, State>> transitionTable = new HashMap<>();

    public void addTransition(State inputTransition, String alphabet, State outputTransition) {
        if (transitionTable.containsKey(inputTransition))
            addTransitionForExistingState(inputTransition, alphabet, outputTransition);
        else
            addNewTransition(inputTransition, alphabet, outputTransition);

    }

    private void addNewTransition(State inputTransition, String alphabet, State outputTransition) {
        HashMap<String, State> alphabetToTransition = new HashMap<>();
        alphabetToTransition.put(alphabet, outputTransition);
        transitionTable.put(inputTransition, alphabetToTransition);
    }

    private void addTransitionForExistingState(State inputTransition, String alphabet, State outputTransition) {
        transitionTable.get(inputTransition).put(alphabet, outputTransition);
    }

    State nextState(State initialState, String s) {
        return transitionTable.get(initialState).get(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransitionTable that = (TransitionTable) o;

        return transitionTable != null ? transitionTable.equals(that.transitionTable) : that.transitionTable == null;

    }

    @Override
    public int hashCode() {
        return transitionTable != null ? transitionTable.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransitionTable{" +
                "transitionTable=" + transitionTable +
                '}';
    }
}
