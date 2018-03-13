package tictactoe.game.Repositories.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnections {

    private final static String USERNAME = "tictacdoe";
    private final static String PASSWORD = "Qa3LAMD!bL?5";
    private final static String CONNSTRING = "jdbc:mysql://den1.mysql1.gear.host/tictacdoe?useSSL=false";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(CONNSTRING, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}