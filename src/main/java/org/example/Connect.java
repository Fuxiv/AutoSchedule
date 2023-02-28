package org.example;

import java.sql.*;

public class Connect {

Connection conn;
    public Connection connect(short linia) {
        String url = "jdbc:sqlite:C:\\Users\\Jackobe\\IdeaProjects\\autoschedule\\src\\main\\java\\org\\example\\" + linia;
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    String czas;
    String outputNazwa_przystanku[]=new String[26];
    int id;
    public void selectAll(){
        String sql = "SELECT id FROM kurs101 order by id desc limit 1";

        try {
            Connection conn = this.connect((short) 813);
            Statement stmt  = conn.createStatement();
            ResultSet rsId    = stmt.executeQuery(sql);
            id = rsId.getInt("id");

            for (int i = 1;i<=id;i++){
                String queryNazwa_przystanku = "SELECT nazwa_przystanku FROM kurs101 where id="+i;
                ResultSet rsNazwaPrzystanku = stmt.executeQuery(queryNazwa_przystanku);
                outputNazwa_przystanku[i] = rsNazwaPrzystanku.getString("nazwa_przystanku");
            }
            // loop through the result set
//            while (rs.next()) {
//                        String nazwa_przystanku = rs.getString("nazwa_przystanku");
//                        czas = rs.getString("czas");
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

