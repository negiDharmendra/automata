package com.tw.step.automata.nfa;

import com.tw.step.automata.util.FiniteAutomataGenerator;
import com.tw.step.automata.util.FiniteAutomataMachine;
import com.tw.step.automata.util.State;
import com.tw.step.automata.util.TransitionTable;

import java.util.HashSet;

class NFAGenerator implements FiniteAutomataGenerator {
    public FiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates) {
        return new NFAMachine(states, alphabets, transitionTable, q1, finalStates);
    }
}
