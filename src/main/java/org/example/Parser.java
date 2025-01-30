package org.example;

import java.util.ArrayList;

public class Parser implements Checkerable{

    private ArrayList<Argument> arguments = new ArrayList<Argument>();
    private ArrayList<String> names = new ArrayList<String>();

    public void getArguments(String[] args){
        int i = 0;
        while (i < args.length) {
            if (isArgument(args[i])){
                String name = args[i].substring(1);
                String value = "";
                if (!"s".equals(args[i]) && !"f".equals(args[i])){
                    value = args[++i];
                }
                Argument arg = new Argument(name, value);
            }else if (isFileTxt(args[i])){
                names.add(args[i]);
            }
            i++;
        }
    }

    public ArrayList<Argument> getArguments(){
        return this.arguments;
    }

    public ArrayList<String> getNames(){
        return this.names;
    }
}
