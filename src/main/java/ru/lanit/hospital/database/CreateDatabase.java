package ru.lanit.hospital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {

    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static String login = "root";
    private static String password = "mySQLp@ssw0rd";
    private static String dbName = "hospital;";


    public static void main(String[] args) throws Exception {
        Class.forName(jdbcDriver);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthapp?user=" + login + "&password=" + password +
                "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        Statement s = conn.createStatement();

        String dropDatabase = "drop database if exists " + dbName;
        String createDatabase = "create database " + dbName;

        s.executeUpdate(dropDatabase);
        s.executeUpdate(createDatabase);
    }
}

