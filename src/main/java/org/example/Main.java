package org.example;

import java.util.Map;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        Parser parser = new Parser(args);
        Data dataset = new Data(parser);
        Writer.writer(dataset, parser);
    }
}