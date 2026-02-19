//psolarski25@georgefox.edu

import java.util.Scanner;

/**
 * @file ChangeMaker.java
 * @brief A utility class to calculate change given a sales amount and an amount tendered
 * @author Payton Solarski (psolarski25@georgefox.edu)
 */
public class ChangeMaker
{
    // Define the number of cents in each denomination as constants
    private static final int ONE_HUNDRED_DOLLARS = 10000;
    private static final int FIFTY_DOLLARS = 5000;
    private static final int TWENTY_DOLLARS = 2000;
    private static final int TEN_DOLLARS = 1000;
    private static final int FIVE_DOLLARS = 500;
    private static final int ONE_DOLLAR = 100;
    private static final int QUARTER = 25;
    private static final int DIME = 10;
    private static final int NICKEL = 5;
    private static final int PENNY = 1;

    /**
     * Main entry point to run the program
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) 
    {
        // Create a new Scanner item, and get amounts from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to my Change Maker program");
        System.out.print("Enter the sales price (xx.xx): $");
        double salesPrice = input.nextDouble();
        System.out.print("Enter the amount tendered (xx.xx): $");
        double amountTendered = input.nextDouble();
        double change = amountTendered - salesPrice;
        System.out.println();
        System.out.println("Your change is: $" + change);
        int changeCents = (int) Math.round((change * ONE_DOLLAR)); // Convert change into cents
        
        // Call printChange method to calculate and print change 
        printChange(changeCents);

    }

    /**
     * Calculates and prints the count of each denomination for the change.
     * @param changeCents The total amount of change in pennies.
     */
    private static void printChange(int changeCents)
    {
        int[] values;
        String[] names;
        String[] namesPlural;

        // Initialize arrays with denomination values in cents and different names for grammar
        values = new int[]
            {
                ONE_HUNDRED_DOLLARS, FIFTY_DOLLARS, TWENTY_DOLLARS, TEN_DOLLARS,
                FIVE_DOLLARS, ONE_DOLLAR, QUARTER, DIME, NICKEL, PENNY
            };
        names = new String[]
            {
                "Hundred Dollar Bill", "Fifty Dollar Bill", "Twenty Dollar Bill",
                "Ten Dollar Bill", "Five Dollar Bill", "One Dollar Bill",
                "Quarter", "Dime", "Nickel", "Penny"
            };
        namesPlural = new String[]
            {
                "Hundred Dollar Bills", "Fifty Dollar Bills", "Twenty Dollar Bills",
                "Ten Dollar Bills", "Five Dollar Bills", "One Dollar Bills",
                "Quarters", "Dimes", "Nickels", "Pennies"
            };

        // For loop to check how many of each denomination are needed for change
        for (int i = 0; i < values.length; i++)
        {
            int count = changeCents / values[i];
            changeCents %= values[i];

            // See if we need to use singular or plural denomination name
            if (count > 0)
            {
                if (count == 1)
                {
                    System.out.println(count + " - " + names[i]);
                }
                else
                {
                    System.out.println(count + " - " + namesPlural[i]);
                }
            }
        }
    }
}
