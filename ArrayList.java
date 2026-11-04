import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @file ArrayList.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A simplified version of class ArrayList
 */
public class ArrayList<E> implements Iterable<E>
{
    private int _size;
    private E[] _array;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList()
    {
        _array =  (E[]) new Object[10];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param initialCapacity int the initial capacity of the array list
     */
    public ArrayList(int initialCapacity)
    {
        // Throw an exception if the capacity is negative
        if (initialCapacity < 0)
        {
            throw new IllegalArgumentException("Initial capacity cannot be negative.");
        }

        _array =  (E[]) new Object[initialCapacity];
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index int the position to add the element
     * @param element E the element to be added
     *
     * @details Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their indices).
     */
    public void add(int index, E element)
    {
        // Throw an exception if the index given is out of range
        if (index < 0 || index > _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        // Grow the array, if needed
        if (_size == _array.length)
        {
            grow();
        }

        // Move everything over to the right
        for (int i = _size; i > index; i--)
        {
            _array[i] = _array[i - 1];
        }

        // Add in the new element to the index
        _array[index] = element;

        _size++;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element E the element to be added
     * @return boolean true
     */
    public boolean add(E element)
    {
        // Call the other add function
        add(_size, element);

        // Return true if it goes through
        return true;
    }

    /**
     * Removes all the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear()
    {
        for (int i = 0; i < _size; i++)
        {
            _array[i] = null;
        }

        // Set the size back to 0
        _size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index int the index of the element to return
     * @return E the element at the specified position in this list
     */
    public E get(int index)
    {
        // Throw an exception if the index given is out of range
        if (index < 0 || index >= _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        return _array[index];
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param element E element to search for
     * @return int the index of the specified element (or -1 if this list does not contain the element)
     */
    public int indexOf(E element)
    {
        // Create an identifier of the index of the element (-1 if not found in list)
        int index = -1;

        // Go through the array to find element
        for (int i = 0; i < _size; i++)
        {
            if (Objects.equals(element, _array[i]))
            {
                index = i;
            }
        }

        // Return -1 if list does not contain the element
        return index;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty()
    {
        return _size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index int the index of the element to be removed
     * @return E the element that was removed from the list
     *
     * @details Shifts any subsequent elements to the left (subtracts one from their indices).
     */
    public E remove(int index)
    {
        // Save the element to be removed
        E removed = _array[index];

        // Throw an exception if the index given is out of range
        if (index < 0 || index >= _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        // Move everything over to the left
        for (int i = index; i < (_size - 1); i++)
        {
            _array[i] = _array[i + 1];
        }

        // Null out the old last slot
        _array[_size - 1] = null;

        _size--;

        return removed;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index int the index of the element to be replaced
     * @param element E the element to be stored at the specified position
     * @return E the element previously at the specified position
     */
    public E set(int index, E element)
    {

        // Remember the old element, then replace it
        E old = _array[index];
        _array[index] = element;

        // Throw an exception if the index given is out of range
        if (index < 0 || index >= _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        return old;
    }

    /**
     * Returns the number of elements in this list.
     * @return int the number of elements in this list
     */
    public int size()
    {
        return this._size;
    }

    /**
     * Doubles the capacity of the array (to at least 1).
     */
    private void grow()
    {
        // Double the capacity (choose max value in case current capacity is 0)
        int newCapacity = Math.max(1, _array.length * 2);
        E[] bigger = (E[]) new Object[newCapacity];

        // Copy over the current arrays contents
        for (int i = 0; i < _size; i++)
        {
            bigger[i] = _array[i];
        }
        _array = bigger;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return Iterator over the elements in this list in proper order
     */
    @Override
    public Iterator<E> iterator()
    {
        return new ArrayListIterator();
    }

    /**
     * Private inner class that provides iterator functionality over the outer.
     */
    private class ArrayListIterator implements Iterator<E>
    {
        private int currentIndex;

        public ArrayListIterator()
        {
            this.currentIndex = 0;
        }

        /**
         * Returns true if the iteration has more elements.
         * @return boolean True if the iteration has more elements
         */
        @Override
        public boolean hasNext()
        {
            return currentIndex < size();
        }

        /**
         * Returns the next element in the iteration.
         * @return E the next element in the iteration
         */
        @Override
        public E next()
        {
            if(!hasNext())
            {
                throw new NoSuchElementException("No more elements remaining in iterator.");
            }

            return _array[currentIndex++];
        }
    }
}
