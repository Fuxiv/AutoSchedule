package org.example;

import java.io.IOException;
import java.nio.file.*;

public class Writer{
    Connect app = new Connect();

    public void write(short linia, short kurs) throws IOException{
        app.connect();
        app.getStopAndTime((short) 813, kurs);

//        Files.copy(Path.of("src/main/java/org/example/beginning.txt"), Path.of("src/main/java/org/example/beginning1.txt"));
//        byte[] a = Files.readAllBytes(Path.of("./Schedules/beginning.txt"));
//        Files.write(Paths.get("./Schedules/" + Utils.nazwaRozkladu("06", "03", linia, kurs) + ".txt"), a);
//        Files.write(Paths.get("./Schedules/" + Utils.nazwaRozkladu("06", "03", linia, kurs) + ".txt"), app.output, StandardOpenOption.APPEND);
    }
}

