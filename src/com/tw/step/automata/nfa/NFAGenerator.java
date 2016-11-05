package com.tw.step.automata.nfa;

import com.tw.step.automata.util.*;

import java.util.HashSet;

public class NFAGenerator implements FiniteAutomataGenerator {
    public FiniteAutomataMachine generate(States states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, States finalStates) {
        return new NFAMachine(states, alphabets, transitionTable, q1, finalStates);
    }
}
