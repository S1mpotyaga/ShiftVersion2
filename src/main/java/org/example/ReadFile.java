package org.example;

import java.util.Scanner;

public interface ReadFile {

    void readFile(String name);

    default Pair<Types, String> tranformation(Scanner in) {
        String currentString = in.nextLine().trim();
        Types key;
        try {
            Long.parseLong(currentString);
            key = Types.INTEGER;
        } catch (NumberFormatException f) {
            try {
                Double.parseDouble(currentString);
                key = Types.FLOAT;
            } catch (NumberFormatException s) {
                key = Types.STRING;
            }
        }
        return new Pair<Types, String>(key, currentString);
    }
}