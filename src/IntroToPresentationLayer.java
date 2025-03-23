package src;
// Author:  Wendy-Beth Minton

import java.util.ArrayList;

// Class:   3810 Database
// Lab:     Introduction to Database Connectivity

// This is an example of a presentation layer. Notice it includes all the needed prompts needed.
// This class also happens to have the main method. It is common to have the entry to a program 
// exist in the presentation layer, and actually each application tends to have its own entry point,
// if the program/product has multiple applications. 

import java.util.Scanner;

public class IntroToPresentationLayer {
    public static void main(String[] args) {
        Scanner userInformation = new Scanner(System.in);
        System.out.println("Enter username and password:");
        // String input
        String userName = userInformation.nextLine();
        String password = userInformation.nextLine();

        // Let's start simple. How do we connect to and access a database?
        // Well, the presentation layer can't do it. We need an instance of the DAL!
        IntroToDAL dal = new IntroToDAL();
        Arcade ar = new Arcade();
        VideoGame vg = new VideoGame();
        int choice = 0;

        while (choice != -1) {
            System.out.println("\nOptions \n1. Single Query on MealPlanning \n"
                    + "2. Single Query on Arcade Games \n" +
                    "3. Single Query on Video Games \n" +
                    "4. Stored Prodecure on Meal Planning no parameter \n5. Stored Prodecure on Meal Planning with parameter \n"
                    +
                    "6. Statement on Arcade Games \n7.Prepared Statement on Acrade Games \n8. Callable Statement on Arcade Games \nInput -1 to quit");
            System.out.println("Enter the number correspoing to what you want to do:");
            choice = userInformation.nextInt();

            System.out.println();

            ArrayList<String> output = new ArrayList<>();

            if (choice == -1) {
                System.out.println("Goodbye");
            } else if (choice == 1) {
                System.out.println("\n\nMealPlanning");
                if (dal.TryExecutingAQuery("MealPlanning", "Select * from Meal", userName, password, output)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);
            } else if (choice == 2) {
                System.out.println("\n\nArcadeGames");

                if (ar.TryExecutingAQuery("ArcadeGames", "Select * from Game", userName, password, output)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);
            } else if (choice == 3) {
                // VideoGames
                System.out.println("\n\nVideoGames");

                if (vg.TryExecutingAQuery("VideoGamesystems", "Select * from game", userName, password, output)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);
            } else if (choice == 4) {
                System.out.println("\n\nMealPlanning");
                if (dal.TryExecutingAStoredProcedure("MealPlanning", userName, password)) {
                    System.out.println("Successfully ran a stored procedure");
                } else {
                    System.out.println("Failed to run a stored procedure");
                }
                // printData(outputM);
            } else if (choice == 5) {
                System.out.println("\n\nMealPlanning");
                if (dal.TryExecutingAStoredProcedureWithParam("MealPlanning", userName, password, "Maple Chicken",
                        "Dude Diet",
                        4, true, "www.dudediet.com")) {
                    System.out.println("Success ran stored procedure with params");
                } else {
                    System.out.println("Failed to run stored procedure with params");
                }
                // printData(outputM);
            } else if (choice == 6) {
                System.out.println("\n\nArcadeGames");
                if (ar.statement("ArcadeGames", userName, password, output)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);

                // printData(outputM);
            } else if (choice == 7) {
                System.out.println("\n\nArcadeGames");
                if (ar.preparedStatement("ArcadeGames", userName, password, output, 2)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);

                // printData(outputM);
            } else if (choice == 8) {
                System.out.println("\n\nArcadeGames");
                if (ar.callableStatement("ArcadeGames", userName, password, output)) {
                    System.out.println("Successfully connected to the database");
                } else {
                    System.out.println("Failed to connect to the database");
                }
                printData(output);

                // printData(outputM);
            } else {
                System.out.println("Invalid input");
            }

        }
    }

    public static void printData(ArrayList<String> output) {
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
    }
}