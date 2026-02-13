// psolarski25@georgefox.edu

import java.util.Scanner;

/**
 * @file Matcher.java
 * @brief A program to find partial substring matches
 * @author psolarski25@georgefox.edu
 */
public class Matcher
{
    /**
     * Main entry point to run the program
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        // Create a scanner object to get inputs from the keyboard
        Scanner input = new Scanner(System.in);

        // Get two words from the user's input
        System.out.print("Enter word: ");
        String word = input.next();
        System.out.print("Enter sub-word: ");
        String subword = input.next();

        // Check to see if subword is a substring of word
        if (word.contains(subword))
        {
            System.out.println(subword + " is a substring of " + word);
        }
        else
        {
            boolean partialSub = false;

            // Loop through word one character at a time
            for (int i = 0; i < word.length(); i++) {
                // Create a temporary string excluding the character at index i
                String tempStr = word.substring(0, i) + word.substring(i + 1);

                if (tempStr.contains(subword)) {
                    partialSub = true;
                    // Stop looping since we found it
                    break;
                }
            }

            // Output results based on boolean partialSub
            if (partialSub)
            {
                System.out.println(subword + " is a partial substring of " + word);
            }
            else
            {
                System.out.println(subword + " is not a substring of " + word);
            }
        }
    }
}
