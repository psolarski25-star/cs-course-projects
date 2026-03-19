//psolarski25@georgefox.edu

/**
 * @file Book.java
 * @brief Represents a Book product, including author and publisher information.
 * @author Payton Solarski
 */
public class Book extends Product
{
    private final String _author;
    private final String _publisher;

    /**
     * Constructs a new Book with the specified details.
     * @param id the unique product ID (e.g., BK_202)
     * @param desc the title of the book
     * @param price the unit price
     * @param author the author of the book
     * @param publisher the publishing company
     */
    public Book(String id, String desc, double price, String author, String publisher)
    {
        super(id, desc, price);
        this._author = author;
        this._publisher = publisher;
    }

    /**
     * Gets back the author of the book.
     * @return the book's author
     */
    public String getAuthor()
    {
        return _author;
    }

    /**
     * Gets back the publisher of the book.
     * @return the book's publisher
     */
    public String getPublisher()
    {
        return _publisher;
    }

    /**
     * Returns the book's details appended to the base product information.
     * @return a string containing product details plus author and publisher.
     */
    @Override
    public String toString()
    {
        return super.toString() + String.format(", Author: %s, Publisher: %s", _author, _publisher);
    }
}
