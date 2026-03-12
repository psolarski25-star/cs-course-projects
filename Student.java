//psolarski25@georgefox.edu

/**
 * @file Student.java
 * @brief Represents a student, extending the basic Person class.
 * This class includes specific information regarding the student's
 * academic status or GPA.
 * @author Payton Solarski
 */
public class Student extends Person
{
    private String _major;
    private double _gpa;

    /**
     * Constructs a Student with personal details plus major and GPA.
     * @param name the student's name
     * @param phone the student's phone
     * @param email the student's email
     * @param major the student's field of study
     * @param gpa the student's grade point average
     */
    public Student(String name, String phone, String email, String major, double gpa)
    {
        super(name, phone, email);
        this._major = major;
        this._gpa = gpa;
    }

    /**
     * Gets the student's major.
     * @return the major string
     */
    public String getMajor()
    {
        return _major;
    }

    /**
     * Gets the student's current GPA.
     * @return the GPA as a double
     */
    public double getGPA()
    {
        return _gpa;
    }

    /**
     * Updates the student's major.
     * @param newMajor the new major to set
     */
    public void setMajor(String newMajor)
    {
        this._major = newMajor;
    }

    /**
     * Updates the student's GPA.
     * @param newGPA the new gpa to set
     */
    public void setGPA(double newGPA)
    {
        this._gpa = newGPA;
    }

    /**
     * Returns a string representation of the student, including personal
     * information and academic data.
     * @return a formatted string with student details
     */
    @Override
    public String toString()
    {
        return super.toString() + ", Major: " + _major + ", GPA: " + _gpa;
    }
}
