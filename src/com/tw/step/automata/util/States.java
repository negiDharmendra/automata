package com.tw.step.automata.util;

import java.util.HashSet;

public class States {
    private HashSet<State> states = new HashSet<>();

    public boolean add(State state) {
        return states.add(state);
    }

    public States intersection(States anotherStates) {
        States intersectionResult = new States();
        this.states.forEach(state -> {
            if(anotherStates.contains(state)) intersectionResult.add(state);
        });
        return intersectionResult;
    }

    private boolean contains(State state) {
        return states.contains(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        States states1 = (States) o;

        return states != null ? states.equals(states1.states) : states1.states == null;

    }

    @Override
    public int hashCode() {
        return states != null ? states.hashCode() : 0;
    }


}
