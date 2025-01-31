package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Data implements ReadFile {
    private Map<Types, ArrayList<String>> dataset = new HashMap<Types, ArrayList<String>>();

    private void addType(Types name, String value) {
        dataset.get(name).add(value);
    }

    private void setType(Types name) {
        dataset.put(name, new ArrayList<String>());
    }

    private void setTypes() {
        Types[] types = Types.values();
        for (Types type : types) {
            setType(type);
        }
    }

    public Data(Parser parser) {
        setTypes();
        for (String name : parser.getNamesFiles()) {
            readFile(name);
        }
    }

    @Override
    public void readFile(String name) {
        try (Scanner in = new Scanner(new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8));) {
            while (in.hasNextLine()) {
                Pair<Types, String> pair = tranformation(in);
                addType(pair.first(), pair.second());
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    public Map<Types, ArrayList<String>> getDataset() {
        return this.dataset;
    }
}
