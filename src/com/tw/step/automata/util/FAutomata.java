package com.tw.step.automata.util;

public enum FAutomata {
    TUPLE("tuple"), DELTA("delta"), NAME("name"), TYPE("type"), PASSCASES("pass-cases"), FAILCASES("fail-cases"), ALPHABETS("alphabets"),
    STARTSTATE("start-state"), FINALSTATES("final-states"), STATES("states"), NFA("nfa"), DFA("dfa");

    private final String name;

    FAutomata(String name) {
        this.name = name;
    }

    public String value() {
        return this.name;
    }
}
