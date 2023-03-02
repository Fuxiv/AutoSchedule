package org.example;

import java.util.Random;
import java.sql.*;
import java.util.ArrayList;


public class Connect {
Connection conn;
    public Connection connect(short linia) {
        String url = "jdbc:sqlite:C:\\Users\\Jackobe\\IdeaProjects\\autoschedule\\src\\main\\java\\org\\example\\"+linia;
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    String czas;
    ArrayList<String> output = new ArrayList<>();
    short id;
    public void selectAll(){
        String getHighestId = "SELECT id FROM kurs101 order by id desc limit 1";

        try {
            Connection conn = this.connect((short)813);
            Statement stmt  = conn.createStatement();
            ResultSet rsId  = stmt.executeQuery(getHighestId);
            id = rsId.getShort("id");
            for (int i = 1;i<=id;i++){
                String queryNazwa_przystanku = "select nazwa_przystanku from kurs101 where id="+i;
                ResultSet rsNazwaPrzystanku = stmt.executeQuery(queryNazwa_przystanku);
                String fromNazwa_przystanku = rsNazwaPrzystanku.getString("nazwa_przystanku");
                String queryCzas = "select czas from kurs101 where id="+i;
                ResultSet rsCzas = stmt.executeQuery(queryCzas);
                String planned = rsCzas.getString("czas");
                Random rand = new Random();
                int actualArrivalSeconds = rand.nextInt(11,48);
                String actualArrival = planned.substring(0,6)+actualArrivalSeconds;
                String arrivalDiff = "00:00:"+actualArrivalSeconds+"  ";
                int actualDepartureSeconds = rand.nextInt(59);
                while(actualDepartureSeconds<actualArrivalSeconds){
                    if (actualArrivalSeconds>=59){
                        actualArrivalSeconds-=11;
                    }
                    actualDepartureSeconds = rand.nextInt(actualArrivalSeconds+10,59);
                }
                String actualDeparture = planned.substring(0,6)+actualDepartureSeconds;
                String actualDepartureDiff = "00:00:"+actualDepartureSeconds;
                Przystanek defaultPrzystanek = new Przystanek(Utils.rightPadding(fromNazwa_przystanku, ' ', 32), Utils.rightPadding(planned, ' ', 10), Utils.rightPadding(actualArrival, ' ', 10), Utils.rightPadding(arrivalDiff, ' ', 10), Utils.rightPadding(planned, ' ', 10), Utils.rightPadding(actualDeparture, ' ', 10), actualDepartureDiff);
                output.add(defaultPrzystanek.nazwa+defaultPrzystanek.arrivalCzas+defaultPrzystanek.actualArrivalCzas+defaultPrzystanek.arrivalDiffCzas+defaultPrzystanek.departureCzas+defaultPrzystanek.actualDepartureCzas+defaultPrzystanek.departureDiffCzas);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

