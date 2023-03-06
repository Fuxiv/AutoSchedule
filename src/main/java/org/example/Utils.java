package org.example;

public class Utils {

    public static String leftPadding(String input, char ch, int L) {
        return String.format("%" + L + "s", input).replace(' ', ch);
    }

    public static String
    rightPadding(String input, char ch, int L) {
        return String.format("%" + (-L) + "s", input).replace(' ', ch);
    }

    public static String nazwaRozkladu(String d, String m, short linia, short kurs) {
        return d + "-" + m + "-" + linia + "-" + kurs;

    }
}