package com.tw.step.automata.util;

public interface FiniteAutomataMachine {
    boolean validate(String languageString) throws InvalidAlphabetException;
}
