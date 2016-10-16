package com.dharmenn.dfaTestRunner;

import com.dharmenn.dfa.DFAGenerator;
import com.dharmenn.dfa.InvalidAlphabetException;
import net.minidev.json.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParseException, InvalidAlphabetException, IOException {
        DFATestRunner dfaTestRunner = new DFATestRunner();
        dfaTestRunner.run("src/examples.json", new DFAGenerator());
    }

}
