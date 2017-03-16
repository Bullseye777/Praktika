package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by A on 2017.03.16.
 */
public class Papildoma {

    private Connection connection;
    Scanner sc = new Scanner(System.in);

    public Papildoma() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aktoriai",
                    "root",
                    ""
            );
            //Statement statementnt = connection.createStatement();
        } catch (Exception klaida) {
            System.out.println(klaida);

        }
    }

    public void Pasisveikint() {
        System.out.println("Sveiki ");
    }

    public void Paklausimas() {
        System.out.println("Pasirinkite ka norite daryti ir iveskite pasirinkimo numeri:");
        System.out.println("1 - prideti duomenis, 2 - trinti duomenis, 3 - atvaizduoti lentele");
    }

    public void Sprendimas() {
        int pasirinkimas = sc.nextInt();
        switch (pasirinkimas) {

            case 1:
                Prideti();
                break;
            case 2:
                Trinti();
                break;
            case 3:
                Atvaizdavimas();
                break;
        }
    }

    public void Prideti() {
        //INSERT INTO `aktoriai` (`id`, `vardas`, `pavarde`, `amzius`) VALUES (NULL, '', '', '')
        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO `aktoriai` (`vardas`, `pavarde`, `amzius`) VALUES (?, ?, ?);");
            System.out.println("Ivesti Varda");
            String vardas = sc.next();
            System.out.println("Ivesti Pavarde");
            String pavarde = sc.next();
            System.out.println("Ivesti amziu");
            String amzius = sc.next();

            statement.setString(1, vardas);
            statement.setString(2, pavarde);
            statement.setString(3, amzius);


            statement.executeUpdate();


        } catch (Exception klaida) {
            System.out.println(klaida);
        }

    }

    public void Trinti() {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `aktoriai` WHERE `aktoriai`.`id` = " + "?" + ";");
            System.out.println("Ivesti ID aktoriaus kuri norite istrinti");
            int id = sc.nextInt();
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    public void Atvaizdavimas() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `aktoriai`;");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print("|");
                System.out.print(resultSet.getString("vardas"));
                System.out.print("|");
                System.out.print(resultSet.getString("pavarde"));
                System.out.print("|");
                System.out.print(resultSet.getInt("amzius"));
                System.out.print("|");
                System.out.println("");
            }
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }
}

