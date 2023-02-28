package org.example;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    Connect app = new Connect();
    app.selectAll();
    System.out.println(app.id);
    System.out.println(app.czas);
        for (int i=1;i<25;i++){
            System.out.println(app.outputNazwa_przystanku[i]);
        }
        }
    }