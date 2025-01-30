package org.example;

record Argument(String name, Object value){
    @Override
    public String toString(){
        return value.toString();
    }
}