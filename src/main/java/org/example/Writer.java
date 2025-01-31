package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.*;
import java.util.List;

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

    public  static List<Double> statisticOfIntegers(ArrayList<String> values){
        List<Double> currentResult =  Arrays.asList(0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (String elem : values) {
            currentResult.set(1, Math.max(currentResult.get(1), Double.parseDouble(elem)));
            currentResult.set(2,Math.min(currentResult.get(2), Double.parseDouble(elem)));
            currentResult.set(0, currentResult.get(0) + (double) Long.parseLong(elem));
        }
        return currentResult;
    }

    public static List<Double> statisticOfFloats(ArrayList<String> values){
        List<Double> currentResult =  Arrays.asList(0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (String elem : values) {
            currentResult.set(1, Math.max(currentResult.get(1), Double.parseDouble(elem)));
            currentResult.set(2,Math.min(currentResult.get(2), Double.parseDouble(elem)));
            currentResult.set(0, currentResult.get(0) + (double) Double.parseDouble(elem));
        }
        return currentResult;
    }

    public static void statisticOfNumbers(Data dataset, boolean fullStatistic) {
        long count = dataset.getDataset().get(Types.INTEGER).size() + dataset.getDataset().get(Types.FLOAT).size();
        List<Double> resFloats = statisticOfFloats(dataset.getDataset().get(Types.FLOAT));
        List<Double> resIntegers = statisticOfIntegers(dataset.getDataset().get(Types.INTEGER));
        double mx = Math.max(resFloats.get(1), resIntegers.get(1));
        double mn = Math.min(resFloats.get(2), resIntegers.get(2));
        double sum = (double) resFloats.get(0) + (double) resIntegers.get(0);
        double avg = sum / (double) count;
        if (fullStatistic) {
            System.out.printf("A minimal number: %s \nA maximal number: %s \nSum of numbers: %s \nAverage: %s \n", mn, mx, sum, avg);
        } else {
            System.out.printf("Count of numbers: %s \n", count);
        }
    }

    public static void statisticOfStrings(ArrayList<String> values, boolean fullStatistic) {
        String mx = "", mn = "";
        for (String elem : values) {
            if (elem.length() > mx.length()) {
                mx = elem;
            }
            if (mn.isEmpty() || elem.length() < mn.length()) {
                mn = elem;
            }
        }
        if (fullStatistic){
            System.out.printf("Count of strings: %s \nSize of smaller string: %s \nSize of biggest string: %s \n", values.size(), mn.length(), mx.length());
        }else{
            System.out.printf("Count of strings: %s \n",values.size());
        }
    }

    public static void printStatistic(Data dataset, boolean fullStatistic){
        Map<Types, ArrayList<String>> values = dataset.getDataset();
        if (!values.get(Types.INTEGER).isEmpty() || !values.get(Types.FLOAT).isEmpty()){
            statisticOfNumbers(dataset, fullStatistic);
        }
        if (!values.get(Types.STRING).isEmpty()){
            statisticOfStrings(values.get(Types.STRING), fullStatistic);
        }
    }
}