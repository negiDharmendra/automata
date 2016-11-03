package com.tw.step.automata.util;

public interface TransitionTable <T> {
    void addTransition(State currentState , String alphabet, T nextState);
    T nextState(State state, String alphabet);
}
