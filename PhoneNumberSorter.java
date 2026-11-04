import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @file PhoneNumberSorter.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A class sorts phone numbers
 *
 * @details A simple command-line utility program to help
 */
public class PhoneNumberSorter
{
    /**
     * Main entry method to the program
     * @param args
     */
    public static void main(String[] args)
    {
        // Exit code 1: No arguments provided
        if (args == null || args.length == 0)
        {
            System.exit(1);
        }

        // Create new array list for numbers
        List<PhoneNumber> phoneNumbers = new ArrayList<>();

        // Parse each command-line argument
        for (String arg : args)
        {
            try
            {
                PhoneNumber number = PhoneNumber.parsePhoneNumber(arg);
                phoneNumbers.add(number);
            }
            catch(IllegalArgumentException e)
            {
                // Exit code 2: One or more arguments are invalid
                System.exit(2);
            }
        }

        // If all arguments are valid, sort them using compareTo
        Collections.sort(phoneNumbers);

        // Print sorted numbers, one per line
        for (PhoneNumber number : phoneNumbers)
        {
            System.out.println(number);
        }

        // Exit code 0: Success
        System.exit(0);
    }
}
