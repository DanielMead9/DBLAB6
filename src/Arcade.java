package src;

import java.sql.*;
import java.util.ArrayList;

public class Arcade {
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

                String myArcadeGame = myRelation.getString("GameName");
                int myId = myRelation.getInt("ID");
                output.add("Tuple Values:" + myArcadeGame + "," + myId);
            }

        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean statement(String databaseName, String user, String password, ArrayList<String> output) {
        try {

            Connection myConnection = getMySQLConnection(databaseName, user, password);
            if (myConnection == null) {
                System.out.println("Failed to get a connection, cannot execute query");
                return false;
            }

            Statement myStatement = myConnection.createStatement();
            String query = "Select * from Player";
            ResultSet myRelation = myStatement.executeQuery(query);

            while (myRelation.next()) {

                String myName = myRelation.getString("UserName");
                int myId = myRelation.getInt("ID");
                output.add("Tuple Values:" + myName + "," + myId);
            }

        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean preparedStatement(String databaseName, String user, String password, ArrayList<String> output,
            int gameNum) {
        try {

            Connection myConnection = getMySQLConnection(databaseName, user, password);
            if (myConnection == null) {
                System.out.println("Failed to get a connection, cannot execute query");
                return false;
            }

            String query = "SELECT * FROM Player WHERE FavoriteGame = ? ";
            PreparedStatement myPS = myConnection.prepareStatement(query);

            myPS.setInt(1, gameNum);
            ResultSet myRelation = myPS.executeQuery(query);

            while (myRelation.next()) {

                String myName = myRelation.getString("UserName");
                int myId = myRelation.getInt("ID");
                output.add("Tuple Values:" + myName + "," + myId);
            }

        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean callableStatement(String databaseName, String user, String password, ArrayList<String> output) {
        try {

            Connection myConnection = getMySQLConnection(databaseName, user, password);
            if (myConnection == null) {
                System.out.println("Failed to get a connection, cannot execute query");
                return false;
            }

            CallableStatement myCS = myConnection.prepareCall("{Call GetScores()}");
            ResultSet myRelation = myCS.executeQuery();

            while (myRelation.next()) {

                int myplayerID = myRelation.getInt("PlayerId");
                int myScore = myRelation.getInt("Score");
                output.add("Tuple Values:" + myplayerID + "," + myScore);
            }

        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

}
