package com.tw.step.automata.dfa;

import com.tw.step.automata.util.FiniteAutomataGenerator;
import com.tw.step.automata.util.FiniteAutomataMachine;
import com.tw.step.automata.util.State;
import com.tw.step.automata.util.TransitionTable;

import java.util.HashSet;

public class DFAGenerator implements FiniteAutomataGenerator {
    public FiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State initialState, HashSet<State> finalStates) {
        return new DFAMachine(states, alphabets, transitionTable, initialState, finalStates);
    }
}
