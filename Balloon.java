//psolarski25@georgefox.edu

/**
 * @file Balloon.java
 * @brief Class to simulate a spherical balloon with uniform radius
 * @author Payton Solarski
 */
public class Balloon
{
    private double _radius;

    /**
     * Creates a Balloon object with an initial radius
     * @param initialRadius The initial radius of the balloon
     */
    public Balloon(double initialRadius)
    {
        if (initialRadius > 0)
        {
            this._radius = initialRadius;
        }
        else
        {
            this._radius = 0.0;
        }
    }

    /**
     * Creates a Balloon object with an initial radius of 1.0
     */
    public Balloon()
    {
        this._radius = 1.0;
    }

    /**
     * Gets the current radius of the balloon
     * @return radius of balloon
     */
    public double getRadius()
    {
        return _radius;
    }

    /**
     * Calculates and returns the balloon's surface area
     * @return the surface area of the balloon
     */
    public double calcSurfaceArea()
    {
        return 4 * (Math.PI * Math.pow(this._radius, 2));
    }

    /**
     * Calculates and returns the balloon's volume
     * @return the volume of the balloon
     */
    public double calcVolume()
    {
        return ((double) 4 / 3) * (Math.PI * Math.pow(this._radius, 3));
    }

    /**
     * Inflates the balloon by a specified number of cubic units.
     * @param cubicUnits the amount of air to add
     */
    public void inflate(double cubicUnits)
    {
        // Get current volume and add the new units
        double newVolume = calcVolume() + cubicUnits;

        // Solve for the new radius: r = cube-root( 3V / 4pi )
        this._radius = Math.pow((3.0 * newVolume) / (4.0 * Math.PI), 1.0 / 3.0);
    }

    /**
     * Deflates the balloon by a specified number of cubic units.
     * Minimum volume is zero.
     * @param cubicUnits the amount of air to remove
     */
    public void deflate(double cubicUnits)
    {
        double newVolume = calcVolume() - cubicUnits;

        // Makes sure the balloon has a minimum volume of 0
        if (newVolume <= 0)
        {
            this._radius = 0.0;
        }
        else
        {
            // Calculate new radius from the remaining volume
            this._radius = Math.pow((3.0 * newVolume) / (4.0 * Math.PI), 1.0 / 3.0);
        }
    }

    /**
     * Returns a string summary of the balloon's state,
     * including radius, surface area, and volume.
     * @return the formatted string
     */
    public String toString()
    {
        return "Balloon with r=" + _radius +
                ", S=" + calcSurfaceArea() +
                ", V=" + calcVolume();
    }
}
