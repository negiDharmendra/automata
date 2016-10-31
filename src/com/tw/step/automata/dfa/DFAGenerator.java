package com.tw.step.automata.dfa;

import com.tw.step.automata.util.State;

import java.util.HashSet;

public class DFAGenerator implements AutomataGenerator {
    public DeterministicFiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates) {
        return new DeterministicFiniteAutomataMachine(states, alphabets, transitionTable, q1, finalStates);
    }
}
