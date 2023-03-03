package org.example;

import java.util.Random;
import java.sql.*;
import java.util.ArrayList;

public class Connect{
    public static short linia;
    public static short kurs;

    public Connection connect(short linia){
        Connect.linia = linia;
        String url = "jdbc:sqlite:C:\\Users\\Jackobe\\IdeaProjects\\autoschedule\\src\\main\\java\\org\\example\\" + linia;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    ArrayList<String> output = new ArrayList<>();
    short id;

    public void getStopAndTime(short linia, short kurs){
        Connect.kurs = kurs;
        String getHighestId = "SELECT id FROM kurs101 order by id desc limit 1";
        try{
            Connection conn = this.connect(linia);
            Statement stmt = conn.createStatement();
            ResultSet rsId = stmt.executeQuery(getHighestId);
            id = rsId.getShort("id");
            for(int i = 1; i <= id; i++){
                String queryNazwa_przystanku = "select nazwa_przystanku from kurs" + Connect.kurs + " where id=" + i;
                ResultSet rsNazwaPrzystanku = stmt.executeQuery(queryNazwa_przystanku);
                String fromNazwa_przystanku = rsNazwaPrzystanku.getString("nazwa_przystanku");
                String queryCzas = "select czas from kurs" + Connect.kurs + " where id=" + i;
                ResultSet rsCzas = stmt.executeQuery(queryCzas);
                String planned = rsCzas.getString("czas");
                Random rand = new Random();
                int actualArrivalSeconds = rand.nextInt(11, 48);
                String actualArrival = planned.substring(0, 6) + actualArrivalSeconds;
                String arrivalDiff = "00:00:" + actualArrivalSeconds + "  ";
                int actualDepartureSeconds = rand.nextInt(59);
                while(actualDepartureSeconds < actualArrivalSeconds){
                    if(actualArrivalSeconds >= 59){
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
//                Files.write(Paths.get("./beginning1.txt"), a.toString().getBytes());
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}


