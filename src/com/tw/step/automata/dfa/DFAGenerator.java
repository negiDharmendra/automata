package com.tw.step.automata.dfa;

import com.tw.step.automata.util.*;

import java.util.HashSet;

public class DFAGenerator implements FiniteAutomataGenerator {
    public FiniteAutomataMachine generate(States states, HashSet<String> alphabets, TransitionTable transitionTable, State initialState, States finalStates) {
        return new DFAMachine(states, alphabets, transitionTable, initialState, finalStates);
    }
}
