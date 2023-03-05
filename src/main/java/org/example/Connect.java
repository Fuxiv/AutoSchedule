package org.example;

import java.util.Random;
import java.sql.*;
import java.util.ArrayList;

public class Connect{
    public static short linia;
    public static short kurs;

    public Connection connect(){
        String url = "jdbc:sqlite:src/main/java/org/example/baza";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }

    ArrayList<String> output = new ArrayList<>();
    int id;

//    public void get(){
//        try {
//            Connection conn = this.connect();
//            Statement stmt = conn.createStatement();
//            for (int i = 2; i<=6; i++){
//                String czasQuery = "select 10"+i+", przystanek from 813 where czas is not null";
//                try{
//                    ResultSet rsczasQuery = stmt.executeQuery(czasQuery);
//                    String przystanek = rsczasQuery.getString("przystanek");
//                    String czas = rsczasQuery.getString("10"+i);
//                    System.out.println(czas);
//                }catch (Exception ee){
//                    ee.printStackTrace();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void getStopAndTime(short linia, short kurs){
        Connect.linia = linia;
        Connect.kurs = kurs;
        //kurs ++ while next table isn't null
        try{

            short kursNumber = 101;
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            for (int j=1;j<=6;j++){
                ResultSet rsHId = stmt.executeQuery("select id from linia813 where k"+kursNumber+" is not null order by id desc limit 1");
                int highestId = rsHId.getInt("id");
                ResultSet rsLowestId = stmt.executeQuery("select id from linia813 where k"+ kursNumber +" is not null");
                int lowestId = rsLowestId.getInt("id");
                System.out.println(lowestId);
                System.out.println(highestId);
                for(int i = lowestId; i <= highestId; i++){
//                    System.out.println(i);
                    ResultSet rsNazwaPrzystanku = stmt.executeQuery("select przystanek from linia813 where id=" + i);
                    String fromNazwa_przystanku = rsNazwaPrzystanku.getString("przystanek");
                    ResultSet rsCzas = stmt.executeQuery("select k"+kursNumber+" from linia813 where id=" + i);
                    String planned = rsCzas.getString("k"+kursNumber);
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
                    }
                    output.clear();
                kursNumber++;

            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}


