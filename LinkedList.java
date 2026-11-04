import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @file LinkedList.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A simplified version of class LinkedList
 */
public class LinkedList<E> implements Iterable<E>
{
    private LinkedListNode _head;
    private LinkedListNode _tail;
    private int _size;
    private int _modCount;

    /**
     * Constructs an empty list.
     */
    public LinkedList()
    {
        this._head = null;
        this._tail = null;
        this._size = 0;
        this._modCount = 0;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their indices).
     * @param index int The index at which the specified element is to be inserted
     * @param element E Element to be inserted
     */
    public void add(int index, E element)
    {
        // Throw an exception if the index given is out of range
        if(index < 0 || index > _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        if(isEmpty())
        {
            // Case 1: Inserting into an empty list
            LinkedListNode newNode = new LinkedListNode(element, null, null);
            _head = newNode;
            _tail = newNode;
        }
        else if(index == 0)
        {
            // Case 2: Inserting at the head of a non-empty list
            LinkedListNode newNode = new LinkedListNode(element, null, _head);
            _head.setPrevious(newNode);
            _head = newNode;
        }
        else if(index == _size)
        {
            // Case 3: Inserting at the tail of a non-empty list
            LinkedListNode newNode = new LinkedListNode(element, _tail, null);
            _tail.setNext(newNode);
            _tail = newNode;
        }
        else
        {
            // Case 4: Inserting in the middle
            LinkedListNode nextNode = getNode(index);
            LinkedListNode prevNode = nextNode.getPrevious();
            LinkedListNode newNode = new LinkedListNode(element, prevNode, nextNode);
            prevNode.setNext(newNode);
            nextNode.setPrevious(newNode);
        }

        _size++;
        _modCount++;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element E Element to be appended to this list
     * @return boolean True
     */
    public boolean add(E element)
    {
        add(_size, element); // Inserts at the end of the list
        return true;
    }

    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    public void clear()
    {
        _head = null;
        _tail = null;
        _size = 0;

        _modCount++;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index int Index of the element to return
     * @return E The element at the specified position in this list
     */
    public E get(int index)
    {
        LinkedListNode node = getNode(index);

        return node.getValue();
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param element E Element to search for
     * @return int The index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    public int indexOf(E element)
    {
        LinkedListNode current = _head;
        int index = 0;
        int result = -1;

        while(current != null && result == -1)
        {
            if(java.util.Objects.equals(element, current.getValue()))
            {
                result = index;
            }

            current = current.getNext();
            index++;
        }

        return result;
    }

    /**
     * Returns true if this list contains no elements.
     * @return boolean True if this list contains no elements
     */
    public boolean isEmpty()
    {
        return _size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Returns the element that was removed from the list.
     * @param index int The index of the element to be removed
     * @return E The element previously at the specified position
     */
    public E remove(int index)
    {
        // Throw an exception if the index given is out of range
        if(index < 0 || index >= _size)
        {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        // Save the element to be removed
        LinkedListNode target = getNode(index);
        E removedValue = target.getValue();

        LinkedListNode prevNode = target.getPrevious();
        LinkedListNode nextNode = target.getNext();

        if(prevNode == null)
        {
            // Removing head
            _head = nextNode;
        }
        else
        {
            prevNode.setNext(nextNode);
        }

        if(nextNode == null)
        {
            // Removing tail
            _tail = prevNode;
        }
        else
        {
            nextNode.setPrevious(prevNode);
        }

        _size--;
        _modCount++;

        return removedValue;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index int Index of the element to replace
     * @param element E Element to be stored at the specified position
     * @return E The element previously at the specified position
     */
    public E set(int index, E element)
    {
        LinkedListNode node = getNode(index);
        E oldValue = node.getValue();
        node.setValue(element);

        return oldValue;
    }

    /**
     * Returns the number of elements in this list.
     * @return int The number of element in this list
     */
    public int size()
    {
        return this._size;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return Iterator over the elements in this list in proper order
     */
    @Override
    public Iterator<E> iterator()
    {
        return new LinkedListIterator(false);
    }

    /**
     * Returns a reversed iterator over the elements in this list in proper sequence.
     * @return Iterator over the elements in this list in proper order
     */
    public Iterator<E> reverseIterator()
    {
        return new LinkedListIterator(true);
    }

    /**
     * Gets the node of the current index
     * @param index The index we're looking for
     * @return LinkedListNode the node of the index
     */
    private LinkedListNode getNode(int index)
    {
        if(index < 0 || index >= _size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }

        LinkedListNode current;

        // Start from head or tail depending on which is closer
        if(index < _size / 2)
        {
            current = _head;
            for(int i = 0; i < index; i++)
            {
                current = current.getNext();
            }
        }
        else
        {
            current = _tail;
            for(int i = _size - 1; i > index; i--)
            {
                current = current.getPrevious();
            }
        }

        return current;
    }

    /**
     * Inner class for linked list iterator creation and use
     */
    private class LinkedListIterator implements Iterator<E>
    {
        private LinkedListNode _current;
        private final int _expectedModCount;
        private final boolean _reverse;

        /**
         * Create a new iterator
         * @param reverse boolean Check if the iterator is reversed or not
         */
        public LinkedListIterator(boolean reverse)
        {
            this._reverse = reverse;
            this._expectedModCount = _modCount;
            this._current = reverse ? _tail : _head;
        }

        /**
         * Check if the modification counts match what they should
         */
        private void checkForComodification()
        {
            if(_modCount != _expectedModCount)
            {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * See if the iterator has a next value
         * @return boolean true if there's a next value, false if not
         */
        @Override
        public boolean hasNext()
        {
            checkForComodification();

            return _current != null;
        }

        /**
         * Returns the next value of the iterator
         * @return E the next value of the iterator
         */
        @Override
        public E next()
        {
            checkForComodification();

            // Throw an exception if there's no next element
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            E value = _current.getValue();

            // Move to previous node if reverse iterator, else next node
            if(!_reverse)
            {
                _current = _current.getNext();
            } else
            {
                _current = _current.getPrevious();
            }

            return value;
        }
    }

    /**
     * Inner class to represent a node in a linked list
     */
    private class LinkedListNode
    {
        private E _value;
        private LinkedListNode _prev;
        private LinkedListNode _next;

        /**
         * Creates an empty linked list node
         */
        public LinkedListNode()
        {
            this._value = null;
            this._next = null;
            this._prev = null;
        }

        /**
         * Creates a node with some value
         * @param value E value in the node
         */
        public LinkedListNode(E value)
        {
            this._value = value;
            this._next = null;
            this._prev = null;
        }

        /**
         * Creates a node with its own, next, and previous values
         * @param value E value in the node
         * @param prev LinkedListNode the previous value
         * @param next LinkedListNode the next value
         */
        public LinkedListNode(E value, LinkedListNode prev, LinkedListNode next)
        {
            this._value = value;
            this._next = next;
            this._prev = prev;
        }

        /**
         * Returns the value of the node
         * @return E the value of the node
         */
        public E getValue()
        {
            return _value;
        }

        /**
         * Returns the previous value of the node
         * @return E the previous value of the node
         */
        public LinkedListNode getPrevious()
        {
            return _prev;
        }

        /**
         * Returns the next value of the node
         * @return E the next value of the node
         */
        public LinkedListNode getNext()
        {
            return _next;
        }

        /**
         * Sets the value of the node
         * @param value E the new value of the node
         */
        public void setValue(E value)
        {
            this._value = value;
        }

        /**
         * Sets the previous value of the node
         * @param prev E the new previous value of the node
         */
        public void setPrevious(LinkedListNode prev)
        {
            this._prev = prev;
        }

        /**
         * Sets the next value of the node
         * @param next E the new next value of the node
         */
        public void setNext(LinkedListNode next)
        {
            this._next = next;
        }
    }
}
