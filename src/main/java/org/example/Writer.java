package org.example;

import java.io.File;
import java.util.Map;
import java.io.*;

public class Writer {

    public static void writer(Data dataset, Parser parser) {
        Types[] types = Types.values();
        File currentDirectory = new File("");
        for (Types type : types) {
            String path = currentDirectory.getAbsolutePath() + File.separator;
            Map<String, String> arguments = parser.getArguments();
            if (arguments.containsKey("o")) {
                File checkDirectory = new File(parser.getArguments().get("o"));
                path = checkDirectory.getAbsolutePath() + File.separator;
            }
            if (arguments.containsKey("p")) {
                path += arguments.get("p");
            }
            path += type.toString() + ".txt";
            boolean addMode = false;
            if (arguments.containsKey("a"))
                addMode = true;
            write(type, path, addMode, dataset);
        }
    }

    public static void write(Types type, String path, boolean addMode, Data dataset) {
        if (!dataset.getDataset().get(type).isEmpty()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, addMode))) {
                for (String val : dataset.getDataset().get(type)) {
                    bufferedWriter.append(val);
                    bufferedWriter.append('\n');
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.printf("There aren't %s in input data.", type);
        }
    }

    public static void printStatistic(){

    }
}