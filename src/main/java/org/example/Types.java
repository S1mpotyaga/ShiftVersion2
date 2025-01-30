package org.example;

enum Types{
    STRING("строка"),
    INTEGER("целое число"),
    FLOAT("вещественное число");

    private final String name;

    private Types(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}