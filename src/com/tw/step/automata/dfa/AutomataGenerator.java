package com.tw.step.dfa;

import java.util.HashSet;

interface AutomataGenerator {
   DeterministicFiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates);
}
