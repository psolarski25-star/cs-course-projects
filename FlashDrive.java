//psolarski25@georgefox.edu

/**
 * @file FlashDrive.java
 * @brief Represents a FlashDrive product, including capacity in gigabytes (GB).
 * @author Payton Solarski
 */
public class FlashDrive extends Product
{
    private final int _capacity;

    /**
     * Constructs a FlashDrive with standard product info plus storage capacity.
     * @param id the unique product ID
     * @param desc the product description
     * @param price the unit price
     * @param capacity the storage capacity in gigabytes (GB)
     */
    public FlashDrive(String id, String desc, double price, int capacity)
    {
        super(id, desc, price);
        this._capacity = capacity;
    }

    /**
     * Gets the capacity of the item.
     * @return the capacity in gigabytes (GB)
     */
    public int getCapacity()
    {
        return _capacity;
    }

    @Override
    public String toString()
    {
        return super.toString() + String.format(", Capacity: %d GB", _capacity);
    }
}
