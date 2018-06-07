package com.dmedeiros.mybill.util;

public class Verification {
    public static boolean isNullOrEmpty(String value) {

        if (value == null || value.isEmpty())
            return true;

        return false;
    }
}
