//psolarski25@georgefox.edu

/**
 * @file WaterBottle.java
 * @brief Represents a WaterBottle product, including capacity in ounces (oz).
 * @author Payton Solarski
 */
public class WaterBottle extends Product
{
    private final int _capacity;

    /**
     * Constructs a WaterBottle with standard product info plus storage capacity.
     * @param id the unique product ID
     * @param desc the product description
     * @param price the unit price
     * @param capacity the storage capacity in ounces (oz)
     */
    public WaterBottle(String id, String desc, double price, int capacity)
    {
        super(id, desc, price);
        this._capacity = capacity;
    }

    /**
     * Gets the capacity of the item.
     * @return the capacity in ounces (oz)
     */
    public int getCapacity()
    {
        return _capacity;
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format(", Capacity: %d oz",  _capacity);
    }
}
