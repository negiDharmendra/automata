package com.tw.step.automata.util;

import java.util.HashSet;

public class States extends HashSet<State>{

    public States intersection(States anotherStates) {
        States intersectionResult = new States();
        this.forEach(state -> {
            if(anotherStates.contains(state)) intersectionResult.add(state);
        });
        return intersectionResult;
    }

}
