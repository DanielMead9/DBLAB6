package src;
// Author:  Wendy-Beth Minton

// Class:   3810 Database
// Lab:     Introduction to Database Connectivity

// THIS IS THE ONLY CLASS THAT SHOULD IMPORT JDBC! 
import java.sql.*;
import java.util.ArrayList;

// This is an example DAL. Notice all the database logic is contained within this class.
public class IntroToDAL {
    // Notice that the databaseName, user and password are passed into this method.
    // We are in the DAL,
    // and we cannot prompt the user for this information. That should be done in
    // the presentation layer
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

                String myRecipeName = myRelation.getString("RecipeName");
                int myIngredientId = myRelation.getInt("IngredientId");
                output.add("Tuple Values:" + myRecipeName + "," + myIngredientId);

                // But, really you should add an instance of the object to the array of objects
            }
        } catch (SQLException exception) {
            System.out.println("Epic Fail Executing a Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean TryExecutingAStoredProcedure(String databaseName, String user, String password) {
        Connection myConnection = getMySQLConnection(databaseName, user, password);
        if (myConnection == null) {
            System.out.println("Failed to get a connection, cannot execute stored procedure");
            return false;
        }
        try {
            CallableStatement myStoredProcedureCall = myConnection.prepareCall("{Call GetRecipes()}");
            ResultSet myResults = myStoredProcedureCall.executeQuery();

            // Iterate over the ResultSet, row by row
            while (myResults.next()) {
                String myRecipeName = myResults.getString("RecipeName");
                String myCookbookName = myResults.getString("CookbookName");
                int numServings = myResults.getInt("TotalServings");
                System.out.println("Tuple Values:" + myRecipeName + "," + myCookbookName + "," + numServings);
            }
        } catch (SQLException myException) {
            System.out.println("Failed to execute stored procedure:" + myException.getMessage());
            return false;
        }
        return true;
    }

    public boolean TryExecutingAStoredProcedureWithParam(String databaseName, String user, String password,
            String recipeName, String CookbookName, int numServings, boolean isBook, String website) {
        Connection myConnection = getMySQLConnection(databaseName, user, password);
        if (myConnection == null) {
            System.out.println("Failed to obstain a valid connection. Stored procedure could not be run");
            return false;
        }
        try {
            CallableStatement myStoredProcedureCall = myConnection.prepareCall("{Call InsertNewRecipe(?, ?, ?, ?, ?)}");
            myStoredProcedureCall.setString(1, recipeName);
            myStoredProcedureCall.setString(2, CookbookName);
            myStoredProcedureCall.setInt(3, numServings);
            myStoredProcedureCall.setBoolean(4, isBook);
            myStoredProcedureCall.setString(5, website);
            myStoredProcedureCall.executeQuery();
        } catch (SQLException myException) {
            System.out.println("Failed to execute stored procedure:" + myException.getMessage());
            return false;
        }
        return true;
    }

}