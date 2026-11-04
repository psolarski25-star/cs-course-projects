/**
 * @file PhoneNumber.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A class that represents a phone number
 */
public class PhoneNumber implements Comparable<PhoneNumber>
{
    private final String _areaCode;
    private final String _prefix;
    private final String _lineNumber;

    /**
     * Constructs a new PhoneNumber object by parsing text.
     * @param areaCode the area code input from the user
     * @param prefix the prefix input from the user
     * @param lineNumber the line number input from the user
     */
    public PhoneNumber(String areaCode, String prefix, String lineNumber)
    {
        if (isValidPhoneNumber(areaCode, prefix, lineNumber))
        {
            // Set object's variables to validated inputs
            this._areaCode = areaCode;
            this._prefix = prefix;
            this._lineNumber = lineNumber;
        }
        else
        {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    /**
     * Gets the area code of the phone number
     * @return String areaCode the area code
     */
    public String getAreaCode()
    {
        return this._areaCode;
    }

    /**
     * Gets the prefix of the phone number
     * @return String areaCode the area code
     */
    public String getPrefix()
    {
        return this._prefix;
    }

    /**
     * Gets the line number of the phone number
     * @return String areaCode the area code
     */
    public String getLineNumber()
    {
        return this._lineNumber;
    }

    /**
     * Gets all digits of phone number, no dashes
     * @return String all digits of phone number
     */
    public String getDigits()
    {
        return this._areaCode + this._prefix + this._lineNumber;
    }

    @Override
    public String toString()
    {
        return String.format("%s-%s-%s", _areaCode, _prefix, _lineNumber);
    }

    @Override
    public int compareTo(PhoneNumber o)
    {
        return this.getDigits().compareTo(o.getDigits());
    }

    /**
     * Method to validate the components of the phone number.
     * @param inputAreaCode the area code input from the user
     * @param inputPrefix the prefix input from the user
     * @param inputLineNumber the line number input from the user
     * @return boolean true if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String inputAreaCode, String inputPrefix, String inputLineNumber)
    {
        // Reject missing input before doing anything else
        if (inputAreaCode == null || inputPrefix == null || inputLineNumber == null)
        {
            return false;
        }

        // Each part must be digits only, with exactly 3, 3, and 4 digits
        if (!inputAreaCode.matches("\\d{3}") || !inputPrefix.matches("\\d{3}") ||
                !inputLineNumber.matches("\\d{4}"))
        {
            return false;
        }

        // Parse String inputs into ints
        int areaCode = Integer.parseInt(inputAreaCode);
        int prefix = Integer.parseInt(inputPrefix);

        // Area code and prefix cannot start with 0 or 1
        // (a 4-digit line number will always be between 0000 and 9999)
        return areaCode >= 200 && areaCode <= 999 && prefix >= 200 && prefix <= 999;
    }

    /**
     * Method to parse a phone number and return it if valid.
     * @param phoneNumber String the input phone number in the format ###-###-####
     * @return PhoneNumber the parsed and validated phone number
     * @throws IllegalArgumentException if the text is null, is not in the format
     *         ###-###-####, or is not a valid phone number
     */
    public static PhoneNumber parsePhoneNumber(String phoneNumber)
    {
        // Reject null and anything that is not exactly ###-###-####
        if (phoneNumber == null || !phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}"))
        {
            throw new IllegalArgumentException("Invalid phone number.");
        }

        String areaCode = phoneNumber.substring(0,3);
        String prefix = phoneNumber.substring(4,7);
        String lineNumber = phoneNumber.substring(8,12);

        if (isValidPhoneNumber(areaCode, prefix, lineNumber))
        {
            return new PhoneNumber(areaCode, prefix, lineNumber);
        }
        else
        {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }
}
