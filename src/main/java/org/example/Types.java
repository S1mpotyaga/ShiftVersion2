package org.example;

import java.util.ArrayList;
import java.util.Arrays;

enum Types{
    STRING("string"),
    INTEGER("integer"),
    FLOAT("float");

    private final String name;

    private Types(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}