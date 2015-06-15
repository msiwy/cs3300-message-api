package main.java.hello;

import java.sql.*;

/**
 * Created by gatsby on 6/14/15.
 */
public class DatabaseConnection {

    public DatabaseConnection() {
        /* //for AWS RDS
        String dbName = "ebdb";
        String userName = "cs3300";
        String password = "J4nOBNgx";
        String hostname = "aa116n4bis3kmng.c1jgqriefhdd.us-east-1.rds.amazonaws.com";
        String port = "3306";
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
                port + "/" + dbName + "?user=" + userName + "&password=" + password;
                */

        String dbName = System.getProperty("mastercorp");  //mastercorp
        String userName = System.getProperty("pikachu"); //pikachu
        String password = System.getProperty("pikapass"); //pikapass
        String hostname = System.getProperty("127.0.0.1");
        String port = System.getProperty("3306");
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
                port + "/" + dbName + "?user=" + userName + "&password=" + password;

        // Load the JDBC Driver
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }

        Connection conn = null;
        Statement setupStatement = null;
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
        int numresults = 0;
        String statement = null;

        try {
            // Create connection to RDS instance
            conn = DriverManager.getConnection(jdbcUrl);

            // Create a table and write two rows
            setupStatement = conn.createStatement();
            String createTable = "CREATE TABLE Beanstalk (Resource char(50));";
            String insertRow1 = "INSERT INTO Beanstalk (Resource) VALUES ('EC2 Instance');";
            String insertRow2 = "INSERT INTO Beanstalk (Resource) VALUES ('RDS Instance');";

            setupStatement.addBatch(createTable);
            setupStatement.addBatch(insertRow1);
            setupStatement.addBatch(insertRow2);
            setupStatement.executeBatch();
            setupStatement.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        try {
            conn = DriverManager.getConnection(jdbcUrl);

            readStatement = conn.createStatement();
            resultSet = readStatement.executeQuery("SELECT Resource FROM Beanstalk;");

            resultSet.first();
            results = resultSet.getString("Resource");
            resultSet.next();
            results += ", " + resultSet.getString("Resource");

            resultSet.close();
            readStatement.close();
            conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            System.out.println("Closing the connection.");
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    }
}
