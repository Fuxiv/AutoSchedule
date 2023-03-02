package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public static String leftPadding(String input, char ch, int L)
    {
        String result = String
                // First left pad the string
                // with space up to length L
                .format("%" + L + "s", input)
                // Then replace all the spaces
                // with the given character ch
                .replace(' ', ch);
        // Return the resultant string
        return result;
    }
    public static String
    rightPadding(String input, char ch, int L)
    {

        String result
                = String

                // First right pad the string
                // with space up to length L
                .format("%" + (-L) + "s", input)

                // Then replace all the spaces
                // with the given character ch
                .replace(' ', ch);

        // Return the resultant string
        return result;
    }
    }