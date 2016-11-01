package com.tw.step.automata.util;

import com.tw.step.automata.dfa.DFAMachine;
import com.tw.step.automata.dfa.TransitionTable;

import java.util.HashSet;

public interface AutomataGenerator {
   DFAMachine generate(HashSet<State> states, HashSet<String> alphabets, TransitionTable transitionTable, State q1, HashSet<State> finalStates);
}
