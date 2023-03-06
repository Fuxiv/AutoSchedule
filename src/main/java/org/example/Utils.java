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

    public static short[] iloscKursow(int linia, int a) {
        switch (linia) {
            case 813: {
                return switch (a) {
                    case 1 -> new short[]{6, 101};
                    case 2 -> new short[]{6, 201};
                    case 3 -> new short[]{6, 301};
                    default -> throw new IllegalStateException("Unexpected value: " + a);
                };
            }
            default:
                throw new IllegalStateException("Unexpected value: " + linia);
        }//that looks cool
//        switch (linia){
//            case 81301, 81303: return 6;
//            default: return 0;
//        }

    }
}