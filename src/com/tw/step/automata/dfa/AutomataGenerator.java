package com.tw.step.automata.dfa;

import com.tw.step.automata.util.State;

import java.util.HashSet;

interface AutomataGenerator {
   DeterministicFiniteAutomataMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates);
}
