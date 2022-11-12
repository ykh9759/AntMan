package com.example.AntMan.utils;

public class Utils {
    public static boolean isNumeric(String s) {
        return s.chars().allMatch(Character::isDigit);
    }
}
