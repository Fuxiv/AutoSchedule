package org.example;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    Connect app = new Connect();
    app.selectAll();
    System.out.println(app.id);
    System.out.println(app.czas);
        for (Object a : app.output) {
            System.out.println(a);
        }
//        System.out.println(app.output.get(2));

        }
    }