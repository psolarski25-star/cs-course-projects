import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @file Queue.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A singly-linked list with "first in, first out" semantics
 */
public class Queue<E> implements Iterable<E>
{
    private QueueNode _head;
    private QueueNode _tail;
    private int _depth;

    /**
     * Constructs an empty queue.
     */
    public Queue()
    {
        this._head = null;
        this._tail = null;
        this._depth = 0;
    }

    /**
     * Adds an element to the tail of the queue.
     * @param element E Element to be enqueued
     * @return boolean True (the element was added)
     */
    public boolean enqueue(E element)
    {
        QueueNode newNode = new QueueNode(element, null);

        if(isEmpty())
        {
            // The only node in the queue is both the head and the tail
            _head = newNode;
        }
        else
        {
            // Link the current tail to the new node
            _tail.setNext(newNode);
        }

        _tail = newNode;
        _depth++;
        return true;
    }

    /**
     * Enqueues each element of the given iterable one at a time.
     * The last element in the iterable ends up at the tail of the queue.
     * @param elements Iterable<E> Elements to be enqueued
     */
    public void enqueueAll(Iterable<E> elements)
    {
        for(E element : elements)
        {
            enqueue(element);
        }
    }

    /**
     * Gets the value of the element at the head of the queue without removing it.
     * @return E The element at the head of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public E head()
    {
        // Throw an exception if queue is empty
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }

        return _head.getValue();
    }

    /**
     * Removes the element at the head of the queue and returns it.
     * @return E The element that was at the head of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public E dequeue()
    {
        // Throw an exception if queue is empty
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }

        // Save the element to be removed
        E removedValue = _head.getValue();

        // Set the head node to be the next node in the queue
        _head = _head.getNext();

        // If the queue is now empty, there is no tail either
        if(_head == null)
        {
            _tail = null;
        }

        _depth--;

        return removedValue;
    }

    /**
     * Removes all the elements from this queue. The queue will be empty after this call returns.
     */
    public void clear()
    {
        _head = null;
        _tail = null;
        _depth = 0;
    }

    /**
     * Returns the number of elements in this queue.
     * @return int The number of elements in this queue
     */
    public int depth()
    {
        return this._depth;
    }

    /**
     * Returns true if this queue contains no elements.
     * @return boolean True if this queue contains no elements
     */
    public boolean isEmpty()
    {
        return _depth == 0;
    }

    /**
     * Returns an iterator over the elements in this queue, from head to tail.
     * Iterating dequeues each element from the head of the queue, so the queue
     * is empty once iteration is complete.
     * @return Iterator<E> An iterator that dequeues elements from this queue
     */
    @Override
    public Iterator<E> iterator()
    {
        return new QueueIterator();
    }

    /**
     * Inner class for queue iteration. Iterating dequeues each element
     * from the head of the queue until the queue is empty.
     */
    private class QueueIterator implements Iterator<E>
    {
        /**
         * See if the iterator has a next value
         * @return boolean true if there's a next value, false if not
         */
        @Override
        public boolean hasNext()
        {
            return !isEmpty();
        }

        /**
         * Returns the next value of the iterator by dequeuing it from the queue
         * @return E the next value of the iterator
         * @throws NoSuchElementException if the queue is empty
         */
        @Override
        public E next()
        {
            // Throw an exception if there's no next element
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            return dequeue();
        }
    }

    /**
     * Inner class to represent a node in the queue's singly-linked list
     */
    private class QueueNode
    {
        private E _value;
        private QueueNode _next;

        /**
         * Creates a node with a value and a reference to the next node
         * @param value E value in the node
         * @param next QueueNode the next node in the queue (null at the tail)
         */
        public QueueNode(E value, QueueNode next)
        {
            this._value = value;
            this._next = next;
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
         * Returns the next node in the queue
         * @return QueueNode the next node, or null if this is the tail
         */
        public QueueNode getNext()
        {
            return _next;
        }

        /**
         * Sets the next node in the queue
         * @param next QueueNode the new next node
         */
        public void setNext(QueueNode next)
        {
            this._next = next;
        }
    }
}
