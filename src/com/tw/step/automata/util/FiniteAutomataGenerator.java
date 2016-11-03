package com.tw.step.automata.util;

import java.util.HashSet;

public interface FiniteAutomataGenerator {
   FiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates);
}
