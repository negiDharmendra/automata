package com.tw.step.automata.nfa;

import com.tw.step.automata.util.InvalidAlphabetException;
import com.tw.step.automata.util.State;

import java.util.HashSet;
import java.util.Optional;
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
        HashSet<State> currentStates = new HashSet<>();
        currentStates.add(initialState);
        if (languageString.isEmpty())
            return isAccepted(currentStates);
        for (String alphabet : languageString.split(""))
            currentStates = alphabetTraverser(currentStates, alphabet);
        return isAccepted(currentStates);
    }

    private HashSet<State> alphabetTraverser(HashSet<State> currentStates, String alphabet) throws InvalidAlphabetException {
        validateAlphabet(alphabet);
        HashSet<State> allNextStates = new HashSet<>();
        for (State epsilonState : nfaTransitionTable.getEpsilonStates(currentStates))
            allNextStates.addAll(nfaTransitionTable.nextStates(epsilonState, alphabet));
        return allNextStates;
    }

    private boolean isAccepted(HashSet<State> currentStates) {
        HashSet<State> epsilonStates = nfaTransitionTable.getEpsilonStates(currentStates);
        return intersectionsOf(epsilonStates, finalStates).isPresent();
    }

    private Optional<State> intersectionsOf(HashSet<State> epsilonStates, HashSet<State> finalStates) {
        Stream<State> states1 = epsilonStates.stream().filter(finalStates::contains);
        return states1.findAny();
    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if (!alphabets.contains(alphabet)) throw new InvalidAlphabetException(alphabet);
    }
}
