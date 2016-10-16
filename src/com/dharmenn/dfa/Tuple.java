package com.dharmenn.dfa;

import java.util.HashSet;

public class Tuple {
    private State startState;

    private HashSet<State> states = new HashSet<>();
    private HashSet<State> finalStates = new HashSet<>();
    private String alphabet;
    private TransitionTable transitiontable;

    public void addStartState(State startState) {
        this.startState = startState;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public void addFinalState(State state) {
        this.finalStates.add(state);
    }

    public void addAlphabet(String alphabet) {
        this.alphabet  = alphabet;
    }

    public void addTransitionTable(TransitionTable transitiontable) {
        this.transitiontable = transitiontable;
    }


}
