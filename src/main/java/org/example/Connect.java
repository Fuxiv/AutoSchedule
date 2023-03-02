package org.example;

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
//przy zapytaniu z sama nazwa przystanku dziala
//przy zapytaniu z czasem i nazwa nie dziala
//czas nie wyrzuca errora, przechodzi checkpointy
//sam czas dziala
            for (int i = 1;i<=id;i++){
                String queryNazwa_przystanku = "select nazwa_przystanku from kurs101 where id="+i;
                String queryCzas = "select czas from kurs101 where id="+i;
                System.out.println("check1");
                ResultSet rsNazwaPrzystanku = stmt.executeQuery(queryNazwa_przystanku);
                ResultSet rsCzas = stmt.executeQuery(queryCzas);
                System.out.println("check2");
                String fromCzas = rsCzas.getString("czas");
                String fromNazwa_przystanku = rsNazwaPrzystanku.getString("nazwa_przystanku");
                System.out.println("check3");
                Przystanek defaultPrzystanek = new Przystanek(Utils.rightPadding(fromNazwa_przystanku, ' ', 32), fromCzas);
                System.out.println("check4");
                output.add(defaultPrzystanek.nazwa+defaultPrzystanek.czas);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

