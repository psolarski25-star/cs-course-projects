//psolarski25@georgefox.edu

/**
 * @file Product.java
 * @brief Represents a generic product in the GFU Store.
 * This class serves as a base for specific product types like Books and Flash Drives.
 * @author Payton Solarski
 */
public abstract class Product implements Comparable<Product>
{
    private final String _id;
    private final String _desc;
    private final double _price;

    /**
     * Constructs a new Product with the specified identification, description, and price.
     * This constructor is called by subclasses (Book, FlashDrive, WaterBottle)
     * to initialize common product fields.
     * @param id the unique product ID (e.g., "BK_202")
     * @param desc a brief description or title of the product
     * @param price the unit price of the product
     */
    public Product(String id, String desc, double price)
    {
        this._id = id;
        this._desc = desc;
        this._price = price;
    }

    /**
     * Gets the unique product identification code.
     * @return the product ID string (e.g., "BK_202").
     */
    public String getID()
    {
        return _id;
    }

    /**
     * Gets the description or title of the product.
     * @return the product description.
     */
    public String getDescription()
    {
        return _desc;
    }

    /**
     * Gets the current unit price of the product.
     * @return the price as a double.
     */
    public double getPrice()
    {
        return _price;
    }

    /**
     * Compares this product with another product based on their unit prices.
     * @param other the product to be compared.
     * @return a negative integer, zero, or a positive integer as this product's
     * price is less than, equal to, or greater than the specified product's price.
     */
    @Override
    public int compareTo(Product other)
    {
        return Double.compare(this._price, other._price);
    }

    /**
     * Returns a string representation of the product including ID, description, and price.
     * @return a formatted string containing basic product details.
     */
    @Override
    public String toString()
    {
        return String.format("ID: %s, Description: %s, Price: $%.2f", _id, _desc, _price);
    }
}

