package org.example;

public interface Checkerable {

    default boolean isArgument(String cur){
        return '-' == cur.charAt(0) && cur.length() > 1;
    }

    default boolean isFileTxt(String name){
        int lastIndexOf = name.lastIndexOf('.');
        if (lastIndexOf == -1){
            return false;
        }
        return ".txt".equals(name.substring(lastIndexOf));
    }
}