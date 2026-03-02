//psolarski25@georgefox.edu

/**
 * @file Car.java
 * @brief A program to simulate cars
 * @author Payton Solarski
 */
public class Car
{
    private double _milesPerGallon;
    private double _fuelTankCapacity;
    private double _fuelInTank;
    private double _odometer;

    /**
     * Create a Car object and initialize variables
     * @param milesPerGallon The amount miles the car can travel on one gallon of fuel
     * @param fuelTankCapacity The amount of the tank can hold, in gallons
     * @param odometer The amount of miles already travelled
     */
    public Car(double milesPerGallon, double fuelTankCapacity, double odometer)
    {
        this._milesPerGallon = milesPerGallon;
        this._fuelTankCapacity = fuelTankCapacity;
        this._odometer = odometer;
        this._fuelInTank = _fuelTankCapacity; // Starts full of fuel
    }

    /**
     * Create a Car object and initialize variables
     * @param milesPerGallon The amount miles the car can travel on one gallon of fuel
     * @param fuelTankCapacity The amount of the tank can hold, in gallons
     */
    public Car(double milesPerGallon, double fuelTankCapacity)
    {
        this._milesPerGallon = milesPerGallon;
        this._fuelTankCapacity = fuelTankCapacity;
        this._odometer = 0.0; // Starts at zero miles
        this._fuelInTank = _fuelTankCapacity;
    }

    /**
     * Return the miles per gallon for Car
     * @return The miles per gallon
     */
    public double getMilesPerGallon()
    {
        return _milesPerGallon;
    }

    /**
     * Return the fuel tank capacity for Car
     * @return The fuel tank capacity
     */
    public double getFuelTankCapacity()
    {
        return _fuelTankCapacity;
    }

    /**
     * Return the amount of fuel in the fuel tank of Car
     * @return The fuel in fuel tank
     */
    public double getFuelInTank()
    {
        return _fuelInTank;
    }

    /**
     * Return the amount of mils on Car's odometer
     * @return The amount of miles on odometer
     */
    public double getOdometer()
    {
        return _odometer;
    }

    /**
     * Adds an amount of fuel to the fuel tank, not exceeding capacity
     * @param gallons The amount of fuel to add
     */
    public void addGas(double gallons)
    {
        this._fuelInTank += gallons;
        if (this._fuelInTank > this._fuelTankCapacity)
        {
            this._fuelInTank = this._fuelTankCapacity;
        }
    }

    /**
     * Fills the fuel tank to its maximum capacity
     */
    public void fillUp()
    {
        this._fuelInTank = this._fuelTankCapacity;
    }

    /**
     * Drives the car a specified distance, consuming fuel
     * If fuel is insufficient, the car drives as far as possible
     * @param miles The distance requested to drive
     * @return true if the full distance was driven, false otherwise
     */
    public boolean drive(double miles)
    {
        double maxDistance = this._fuelInTank * this._milesPerGallon;
        boolean madeIt = true;

        if (miles > maxDistance)
        {
            // Can't make it; drives as far as can on remaining fuel
            this._odometer += maxDistance;
            this._fuelInTank = 0.0;
            madeIt =  false;
        }
        else
        {
            // Made it; increment odometer and subtract fuel used
            this._odometer += miles;
            this._fuelInTank -= (miles / this._milesPerGallon);
        }

        return madeIt;
    }

    /**
     * Returns a string representation of the car's current state,
     * including MPG, capacity, fuel level, and odometer reading
     * @return a formatted string describing the car
     */
    public String toString()
    {
        return "Car with mpg=" + _milesPerGallon +
                ", cap=" + _fuelTankCapacity +
                ", level=" + _fuelInTank +
                ", odometer=" + _odometer;
    }
}
