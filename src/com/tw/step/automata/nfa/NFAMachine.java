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


    boolean validate(String languageString) throws InvalidAlphabetException {
        String[] alphabetSequence = languageString.split("");
        HashSet<State> states = alphabetTraverses(alphabetSequence);
        Stream<State> states1 = states.stream().filter(finalStates::contains);
        return states1.findAny().isPresent();
    }

    private HashSet<State> alphabetTraverses(String[] alphabetSequence) throws InvalidAlphabetException {
        boolean firstAlphabet = true;
        HashSet<State> states = new HashSet<>();
        for (String alphabet : alphabetSequence) {
            validateAlphabet(alphabet);
            if (firstAlphabet) {
                states = this.nfaTransitionTable.nextStates(initialState, alphabet);
                firstAlphabet = false;
            } else {
                states = traversAfterSecondAlphabet(states, alphabet);
            }
        }
        return states;
    }

    private HashSet<State> traversAfterSecondAlphabet(HashSet<State> states, String s) {
        HashSet<State> result = new HashSet<>();
        for (State state : states) {
            HashSet<State> nextState = this.nfaTransitionTable.nextStates(state, s);
            if (nextState != null) result.addAll(nextState);
        }
        return result;
    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if(! alphabets.contains(alphabet)) throw new InvalidAlphabetException(alphabet);
    }
}
