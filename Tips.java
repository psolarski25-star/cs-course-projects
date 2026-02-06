//psolarski25@georgefox.edu

import java.util.Scanner;

/**
 * @file Tips.java
 * @brief A program to calculate the appropriate tip at a restaurant
 * @author Payton Solarski (psolarski25@georgefox.edu)
 */
public class Tips
{
    /**
     * Main entry point to run the program
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        // Create new Scanner item to get inputs from user,
        // and ask for bill and service quality
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the tip calculator!");
        System.out.print("Enter the bill amount: ");
        double billAmount = input.nextDouble();
        System.out.print("Enter the service level: ");
        String serviceLevel = input.next();

        // Calculate tip percentage, amount, and total bill based on service quality
        // using tipPercent method
        double tipPercent = tipPercent(serviceLevel);
        double tipAmount = billAmount * tipPercent;
        double totalBillAmount = billAmount + tipAmount;

        // Display results from calculations above
        System.out.println();
        System.out.printf("The tip percent is %.0f%%%n", (tipPercent * 100));
        System.out.printf("The tip amount is $%.2f%n", tipAmount);
        System.out.printf("The new total is $%.2f%n", totalBillAmount);

    }

    /**
     * Method that calculates the tip percent and amount based on service quality
     * @param serviceLevel
     * @return
     */
    public static double tipPercent(String serviceLevel)
    {
        // Initialize tipPercent variable
        double tipPercent;

        // Calculate percent based on service quality
        if (serviceLevel.equals("great"))
        {
            tipPercent = .25;
        }
        else if (serviceLevel.equals("good"))
        {
            tipPercent = .2;
        }
        else if (serviceLevel.equals("ok"))
        {
            tipPercent = .15;
        }
        else if (serviceLevel.equals("bad"))
        {
            tipPercent = .1;
        }
        else
        {
            tipPercent = .0;
        }

        return tipPercent;
    }
}
