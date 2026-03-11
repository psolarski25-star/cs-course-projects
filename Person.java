//psolarski25@georgefox.edu

/**
 * @file Person.java
 * @brief Represents a general person with basic identification details.
 * This serves as the superclass for more specific types like Students and Employees.
 * * @author Payton Solarski
 */
public class Person
{
    private String _name;
    private String _phone;
    private String _email;

    /**
     * Constructs a Person with a name, phone number, and email
     * @param name the name of the person
     * @param phone their phone number
     * @param email their email address
     */
    public Person(String name, String phone, String email)
    {
        this._name = name;
        this._phone = phone;
        this._email = email;
    }

    /**
     * Gets the person's name
     * @return the name string
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Gets the person's phone number
     * @return the phone number string
     */
    public String getPhone()
    {
        return _phone;
    }

    /**
     * Gets the person's email address
     * @return the email address string
     */
    public String getEmail()
    {
        return _email;
    }

    /**
     * Sets a new name for the person
     * @param newName the name to set
     */
    public void setName(String newName)
    {
        this._name = newName;
    }

    /**
     * Sets a new phone number for the person
     * @param newPhone the phone number to set
     */
    public void setPhone(String newPhone)
    {
        this._phone = newPhone;
    }

    /**
     * Sets a new email address for the person
     * @param newEmail the name to set
     */
    public void setEmail(String newEmail)
    {
        this._email = newEmail;
    }

    /**
     * Returns a string representation of the person
     * @return a formatted string with name, phone, and email
     */
    @Override
    public String toString()
    {
        return "Name: " + _name + ", Phone: " + _phone + ", Email: " + _email;
    }
}
