//psolarski25@georgefox.edu

/**
 * @file InventoryLineItem.java
 * @brief Represents a specific entry in the store inventory, linking a product
 * with its quantity on hand and calculating applicable discounts.
 * @author Payton Solarski
 */
public class InventoryLineItem implements Comparable<InventoryLineItem>
{
    private static long _lineItemCount = 0;
    private final long _lineItemNumber;
    private final Product _product;
    private int _quantity;

    /**
     * Constructs a new InventoryLineItem and associates it with a specific Product.
     * This constructor automatically assigns a unique, monotonically increasing
     * line item number by incrementing the global internal counter.
     * @param product the Product object to be stored in this line item
     * @param quantity the number of units currently on hand
     */
    public InventoryLineItem(Product product, int quantity)
    {
        _lineItemCount++;
        this._lineItemNumber = _lineItemCount;
        this._product = product;
        this._quantity = quantity;
    }

    /**
     * Constructs a new InventoryLineItem and associates it with a specific Product.
     * This constructor automatically assigns a unique, monotonically increasing
     * line item number by incrementing the global internal counter.
     * @param product the Product object to be stored in this line item
     */
    public InventoryLineItem(Product product)
    {
        _lineItemCount++;
        this._lineItemNumber = _lineItemCount;
        this._product = product;
    }

    /**
     * Gets the unique line item number assigned to this entry.
     * @return the automatically generated long ID for this line.
     */
    public long getLineItemNumber()
    {
        return _lineItemNumber;
    }

    /**
     * Gets the product associated with this inventory line.
     * @return the Product object.
     */
    public Product getProduct()
    {
        return _product;
    }

    /**
     * Gets the number of units currently in stock for this line item.
     * @return the quantity on hand.
     */
    public int getQuantity()
    {
        return _quantity;
    }

    /**
     * Calculates the discount rate based on the quantity on hand.
     * 10% for 10+ units, 20% for 25+ units, 25% for 50+ units.
     * @return the discount rate as a decimal (e.g., 0.10 for 10%).
     */
    public double calcDiscountRate()
    {
        if (this._quantity >= 50) return 0.25;
        if (this._quantity >= 25) return 0.20;
        if (this._quantity >= 10) return 0.10;
        return 0.0;
    }

    /**
     * Calculates the total price for this line item, applying any quantity discounts.
     * @return the subtotal of the items
     */
    public double calcSubtotal()
    {
        double fullPrice = _product.getPrice() * _quantity;
        double discountAmount = fullPrice * calcDiscountRate();
        return fullPrice - discountAmount;
    }

    /**
     * Compares this line item to another based on their calculated subtotals.
     * @param other the line item to be compared.
     * @return the result of the subtotal comparison.
     */
    @Override
    public int compareTo(InventoryLineItem other)
    {
        return Double.compare(this.calcSubtotal(), other.calcSubtotal());
    }

    /**
     * Returns a formatted summary of the line item, including the unique line number,
     * product details, quantity, and final subtotal.
     * @return a detailed string representation of this inventory entry.
     */
    @Override
    public String toString() {
        return String.format("Line %d: [%s] Quantity: %d, Subtotal: $%.2f",
                _lineItemNumber, _product.toString(), _quantity, calcSubtotal());
    }
}
