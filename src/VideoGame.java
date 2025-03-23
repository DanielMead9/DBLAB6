package src;

import java.sql.*;
import java.util.ArrayList;

public class VideoGame {
    private Connection getMySQLConnection(String databaseName, String user, String password) {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database" + exception.getMessage());
            return null;
        }
    }

    public boolean TryExecutingAQuery(String databaseName, String query, String user, String password,
            ArrayList<String> output) {
        try {

            Connection myConnection = getMySQLConnection(databaseName, user, password);
            if (myConnection == null) {
                System.out.println("Failed to get a connection, cannot execute query");
                return false;
            }

            Statement myStatement = myConnection.createStatement();
            ResultSet myRelation = myStatement.executeQuery(query);

            while (myRelation.next()) {

                String myGame = myRelation.getString("GameName");
                int mySeries = myRelation.getInt("Series");
                output.add("Tuple Values:" + myGame + "," + mySeries);

            }

        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

}
