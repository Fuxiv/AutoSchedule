package org.example;

public class Przystanek {
    String nazwa;
    String arrivalCzas;
    String actualArrivalCzas;
    String arrivalDiffCzas;
    String departureCzas;
    String actualDepartureCzas;
    String departureDiffCzas;
    Przystanek(String nazwa, String arrivalCzas, String actualArrivalCzas, String arrivalDiffCzas, String departureCzas, String actualDepartureCzas, String departureDiffCzas){
    this.nazwa = nazwa;
    this.arrivalCzas = arrivalCzas;
    this.actualArrivalCzas = actualArrivalCzas;
    this.arrivalDiffCzas = arrivalDiffCzas;
    this.departureCzas = departureCzas;
    this.actualDepartureCzas = actualDepartureCzas;
    this.departureDiffCzas = departureDiffCzas;
    }
}
