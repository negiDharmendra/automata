package com.tw.step.automata.util;

import java.util.HashSet;

public interface FiniteAutomataGenerator {
   FiniteAutomataMachine generate(States states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, States finalStates);
}
