package com.tw.step.automata.nfa;

import com.tw.step.automata.util.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

class NFAMachine implements FiniteAutomataMachine{
    private final States allStates;
    private final HashSet<String> alphabets;
    private final NFATransitionTable nfaTransitionTable;
    private final State initialState;
    private final States finalStates;

    NFAMachine(States allStates, HashSet<String> alphabets, TransitionTable nfaTransitionTable, State initialState, States finalStates) {

        this.allStates = allStates;
        this.alphabets = alphabets;
        this.nfaTransitionTable = (NFATransitionTable)nfaTransitionTable;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }


    public boolean validate(String languageString) throws InvalidAlphabetException {
        States currentStates = new States();
        currentStates.add(initialState);
        if (languageString.isEmpty())
            return isAccepted(currentStates);
        for (String alphabet : languageString.split(""))
            currentStates = alphabetTraverser(currentStates, alphabet);
        return isAccepted(currentStates);
    }

    private States alphabetTraverser(States currentStates, String alphabet) throws InvalidAlphabetException {
        validateAlphabet(alphabet);
        States allNextStates = new States();
        for (State epsilonState : nfaTransitionTable.getEpsilonStates(currentStates))
            allNextStates.addAll(nfaTransitionTable.nextState(epsilonState, alphabet));
        return allNextStates;
    }

    private boolean isAccepted(States currentStates) {
        States epsilonStates = nfaTransitionTable.getEpsilonStates(currentStates);
        return !finalStates.intersection(epsilonStates).isEmpty();
    }

    private void validateAlphabet(String alphabet) throws InvalidAlphabetException {
        if (!alphabets.contains(alphabet)) throw new InvalidAlphabetException(alphabet);
    }
}
