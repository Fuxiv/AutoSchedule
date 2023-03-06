package org.example;

import java.util.Random;
import java.sql.*;
import java.util.ArrayList;

public class Connect {

    public Connection connect() {
        String url = "jdbc:sqlite:src/main/java/org/example/baza";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }

    ArrayList<String> output = new ArrayList<>();

    public void getStopAndTime(short liniaNumber, byte brygada) {
        short[] iloscKursow = Utils.iloscKursow(liniaNumber,brygada);
        System.out.println(iloscKursow[0]);
        System.out.println(iloscKursow[1]);
        short kursNumber = iloscKursow[1];
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            for (int j = 1; j <= iloscKursow[0]; j++) {
                ResultSet rsHId = stmt.executeQuery("select id from linia" + liniaNumber + " where k" + kursNumber + " is not null order by id desc limit 1");
                int highestId = rsHId.getInt("id");
                ResultSet rsLowestId = stmt.executeQuery("select id from linia" + liniaNumber + " where k" + kursNumber + " is not null");
                int lowestId = rsLowestId.getInt("id");
//                System.out.println(lowestId);
//                System.out.println(highestId);
                for (int i = lowestId; i <= highestId; i++) {
                    ResultSet rsNazwaPrzystanku = stmt.executeQuery("select przystanek from linia813 where id=" + i);
                    String fromNazwa_przystanku = rsNazwaPrzystanku.getString("przystanek");
                    ResultSet rsCzas = stmt.executeQuery("select k" + kursNumber + " from linia813 where id=" + i);
                    String planned = rsCzas.getString("k" + kursNumber);
                    if (fromNazwa_przystanku.length() > 30) {
                        fromNazwa_przystanku = fromNazwa_przystanku.substring(0, 30);
                    }
                    Random rand = new Random();
                    int actualArrivalSeconds = rand.nextInt(11, 48);
                    String actualArrival = planned.substring(0, 6) + actualArrivalSeconds;
                    String arrivalDiff = "00:00:" + actualArrivalSeconds + "  ";
                    int actualDepartureSeconds = rand.nextInt(59);
                    while (actualDepartureSeconds < actualArrivalSeconds) {
                        if (actualArrivalSeconds >= 59) {
                            actualArrivalSeconds -= 11;
                        }
                        actualDepartureSeconds = rand.nextInt(actualArrivalSeconds + 10, 59);
                    }
                    String actualDeparture = planned.substring(0, 6) + actualDepartureSeconds;
                    String actualDepartureDiff = "00:00:" + actualDepartureSeconds;
                    Przystanek defaultPrzystanek = new Przystanek(Utils.rightPadding("  " + fromNazwa_przystanku, ' ', 34), Utils.rightPadding(planned, ' ', 10), Utils.rightPadding(actualArrival, ' ', 10), Utils.rightPadding(arrivalDiff, ' ', 10), Utils.rightPadding(planned, ' ', 10), Utils.rightPadding(actualDeparture, ' ', 10), Utils.rightPadding(actualDepartureDiff, ' ', 10), "ok");
                    output.add(defaultPrzystanek.nazwa + defaultPrzystanek.arrivalCzas + defaultPrzystanek.actualArrivalCzas + defaultPrzystanek.arrivalDiffCzas + defaultPrzystanek.departureCzas + defaultPrzystanek.actualDepartureCzas + defaultPrzystanek.departureDiffCzas + defaultPrzystanek.status);
                }
                    for(Object a : output){
                        System.out.println(a);
                    }
                Writer.write(liniaNumber, kursNumber, output);
                output.clear();
                kursNumber++;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


