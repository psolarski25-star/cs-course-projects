import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @file Stack.java
 * @author Payton Solarski (psolarski25@georgefox.edu)
 * @brief A singly-linked list with "last in, first out" semantics
 */
public class Stack<E> implements Iterable<E>
{
    private StackNode _topNode;
    private int _depth;

    /**
     * Constructs an empty stack.
     */
    public Stack()
    {
        this._topNode = null;
        this._depth = 0;
    }

    /**
     * Pushes an element onto the top of the stack.
     * @param element E Element to be pushed
     * @return boolean True (the element was added)
     */
    public boolean push(E element)
    {
        _topNode = new StackNode(element, _topNode);
        _depth++;
        return true;
    }

    /**
     * Pushes each element of the given iterable onto the stack, one at a time.
     * The last element in the iterable ends up on top.
     * @param elements Iterable<E> Elements to be pushed
     */
    public void pushAll(Iterable<E> elements)
    {
        for(E element : elements)
        {
            push(element);
        }
    }

    /**
     * Gets the value of the element at the top of the stack without removing it.
     * @return E The element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E peek()
    {
        // Throw an exception if stack is empty
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        return _topNode.getValue();
    }

    /**
     * Removes the element at the top of the stack and returns it.
     * @return E The element that was at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E pop()
    {
        // Throw an exception if stack is empty
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        // Save the element to be removed
        E removedValue = _topNode.getValue();

        // Set the top node to be the next node in the stack
        _topNode = _topNode.getNext();

        _depth--;

        return removedValue;
    }

    /**
     * Removes all the elements from this stack. The stack will be empty after this call returns.
     */
    public void clear()
    {
        _topNode = null;
        _depth = 0;
    }

    /**
     * Returns the number of elements in this stack.
     * @return int The number of elements in this stack
     */
    public int depth()
    {
        return this._depth;
    }

    /**
     * Returns true if this stack contains no elements.
     * @return boolean True if this stack contains no elements
     */
    public boolean isEmpty()
    {
        return _depth == 0;
    }

    /**
     * Returns an iterator over the elements in this stack, from top to bottom.
     * Iterating pops each element from the top of the stack, so the stack
     * is empty once iteration is complete.
     * @return Iterator<E> An iterator that pops elements from this stack
     */
    @Override
    public Iterator<E> iterator()
    {
        return new StackIterator();
    }

    /**
     * Inner class for stack iteration. Iterating pops each element
     * from the top of the stack until the stack is empty.
     */
    private class StackIterator implements Iterator<E>
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
         * Returns the next value of the iterator by popping it from the stack
         * @return E the next value of the iterator
         * @throws NoSuchElementException if the stack is empty
         */
        @Override
        public E next()
        {
            // Throw an exception if there's no next element
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            return pop();
        }
    }

    /**
     * Inner class to represent a node in the stack's singly-linked list
     */
    private class StackNode
    {
        private E _value;
        private StackNode _next;

        /**
         * Creates a node with a value and a reference to the next node
         * @param value E value in the node
         * @param next StackNode the next node in the stack (null at the bottom)
         */
        public StackNode(E value, StackNode next)
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
         * Returns the next node in the stack
         * @return StackNode the next node, or null if this is the bottom
         */
        public StackNode getNext()
        {
            return _next;
        }
    }
}
