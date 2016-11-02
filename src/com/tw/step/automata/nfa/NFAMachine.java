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
        HashSet<State> currentStates = new HashSet<>();
        currentStates.add(initialState);
        if (languageString.isEmpty()) {
            currentStates = nfaTransitionTable.getEpsilonStates(initialState);
            Stream<State> states1 = currentStates.stream().filter(finalStates::contains);
            return states1.findAny().isPresent();
        } else {
            for (String alphabet : alphabetSequence) {
                validateAlphabet(alphabet);
                HashSet<State> allNextStates = new HashSet<>();
                for (State state : currentStates) {
                    HashSet<State> epsilonStates = nfaTransitionTable.getEpsilonStates(state);
                    for (State epsilonState : epsilonStates) {
                        HashSet<State> nextStates = nfaTransitionTable.nextStates(epsilonState, alphabet);
                        if (nextStates != null)
                            allNextStates.addAll(nextStates);
                    }
                }
                currentStates = allNextStates;
            }
            HashSet<State> objects = new HashSet<>();
            currentStates.forEach((state) -> {
                HashSet<State> epsilonStates1 = nfaTransitionTable.getEpsilonStates(state);
                if (epsilonStates1 != null) objects.addAll(epsilonStates1);
            });
            Stream<State> states1 = objects.stream().filter(finalStates::contains);
            return states1.findAny().isPresent();
        }


    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if (alphabet.isEmpty()) return;
        if (!alphabets.contains(alphabet)) throw new InvalidAlphabetException(alphabet);
    }
}
