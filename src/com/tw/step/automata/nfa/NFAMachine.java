package com.tw.step.automata.nfa;

import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.State;

import java.util.HashSet;
import java.util.stream.Stream;

class NFAMachine {
    private final HashSet<State> allStates;
    private final HashSet<String> alphabets;
    private final NFATransitionTable nfaTransitionTable;
    private final State initialState;
    private final HashSet<State> finalStates;

    NFAMachine(HashSet<State> allStates, HashSet<String> alphabets, NFATransitionTable nfaTransitionTable, State initialState, HashSet<State> finalStates) {

        this.allStates = allStates;
        this.alphabets = alphabets;
        this.nfaTransitionTable = nfaTransitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }


    public boolean validate(String a) {
        String[] split = a.split("");
        HashSet<State> states = null;
        boolean firstAlphabet = true;
        for (String s : split) {
            HashSet<State> result = new HashSet<>();
            if (firstAlphabet) {
                states = this.nfaTransitionTable.nextStates(initialState, s);
                firstAlphabet = false;
            } else {
                for (State state : states) {
                    HashSet<State> states1 = this.nfaTransitionTable.nextStates(state, s);
                    if (states1 != null) result.addAll(this.nfaTransitionTable.nextStates(state, s));
                }
                states = result;
            }
        }

        assert states != null;
        Stream<State> states1 = states.stream().filter(finalStates::contains);
        return states1.findAny().isPresent();
    }
}
