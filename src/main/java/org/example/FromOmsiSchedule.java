package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//get all bus stop names from a ttp file printed out to the console

public class FromOmsiSchedule {
    public void readFromSchedule(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./SchedulesTTL/" + fileName));
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("[station]")) {
                    line = reader.readLine();
                    line = reader.readLine();
                    line = reader.readLine();
                    System.out.println(line);
                } else {
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
