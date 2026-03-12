//psolarski25@georgefox.edu

/**
 * @file Employee.java
 * @brief Represents an employee, extending the basic Person class.
 * This class tracks professional details such as salary or office location.
 * @author Payton Solarski
 */
public class Employee extends Person
{
    private String _employeeID;

    /**
     * Constructs an Employee with personal details and an employee ID.
     * * @param name the employee's name
     * @param phone the employee's phone
     * @param email the employee's email
     * @param employeeID the unique identifier for the employee
     */
    public Employee(String name, String phone, String email, String employeeID)
    {
        super(name, phone, email);
        this._employeeID = employeeID;
    }

    /**
     * Gets the employee ID.
     * @return the employee ID string
     */
    public String getEmployeeID()
    {
        return _employeeID;
    }

    /**
     * Sets a new employee ID.
     * @param newEmployeeID the ID to set
     */
    public void setEmployeeID(String newEmployeeID)
    {
        this._employeeID = newEmployeeID;
    }

    /**
     * Returns a string representation of the employee, including personal
     * information and their ID.
     * @return a formatted string with employee details
     */
    @Override
    public String toString()
    {
        return super.toString() + ", Employee ID: " + _employeeID;
    }
}
