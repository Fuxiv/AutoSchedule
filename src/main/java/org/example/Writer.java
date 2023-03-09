package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class Writer {

    public static void write(short linia, short kurs, ArrayList<String> output) throws IOException {
//        Files.copy(Path.of("src/main/java/org/example/beginning.txt"), Path.of("src/main/java/org/example/beginning1.txt"));
        byte[] a = Files.readAllBytes(Path.of("./Schedules/beginning.txt"));
        Files.write(Paths.get("./Schedules/" + Utils.nazwaRozkladu("07", "03", linia, kurs) + ".txt"), a);
        Files.write(Paths.get("./Schedules/" + Utils.nazwaRozkladu("07", "03", linia, kurs) + ".txt"), output, StandardOpenOption.APPEND);
    }
}

