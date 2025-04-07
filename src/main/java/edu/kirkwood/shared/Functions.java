package edu.kirkwood.shared;

import java.util.Arrays;

public class Functions {
    public static boolean contains(String[] arr, String item) {
        if(arr == null) {
            return false;
        }
        return Arrays.asList(arr).contains(String.valueOf(item));
    }
}
