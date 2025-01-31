package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Parser implements Checkerable {

    private Map<String, String> arguments = new HashMap<String, String>();
    private ArrayList<String> namesFiles = new ArrayList<String>();

    public Parser(String[] args) {
        int i = 0;
        while (i < args.length) {
            if (isArgument(args[i])) {
                String name = args[i].substring(1);
                String value = "";
                if (!"a".equals(name) && !"s".equals(name) && !"f".equals(name)) {
                    value = args[++i];
                }
                arguments.put(name, value);
            } else if (isFileTxt(args[i])) {
                namesFiles.add(args[i]);
            }
            i++;
        }
    }

    public Map<String, String> getArguments() {
        return this.arguments;
    }

    public ArrayList<String> getNamesFiles() {
        return this.namesFiles;
    }
}
