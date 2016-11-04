package com.tw.step.automata.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatesTest {

    @Test
    public void shouldReturnIntersectionResult() throws Exception {
        States states1 = new States();
        states1.add(new State("q1"));
        states1.add(new State("q2"));

        States states2 = new States();
        states2.add(new State("q3"));
        states2.add(new State("q2"));

        States expected = new States();
        expected.add(new State("q2"));

        assertEquals(expected, states1.intersection(states2));

    }

    @Test
    public void shouldReturnEmptyIntersectionResult() throws Exception {
        States states1 = new States();
        states1.add(new State("q1"));
        states1.add(new State("q2"));

        States states2 = new States();
        states2.add(new State("q3"));
        states2.add(new State("q4"));

        States expected = new States();
        assertEquals(expected, states1.intersection(states2));

    }
}