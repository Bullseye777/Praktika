package com.company;

public class Main {

    public static void main(String[] args) {

        Papildoma objektas = new Papildoma();
        objektas.Pasisveikint();

        while (true) {
            objektas.Paklausimas();
            objektas.Sprendimas();
        }
    }
}
