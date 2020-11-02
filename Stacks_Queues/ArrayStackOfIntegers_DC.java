
/**
 * A stack is a collection that is based on the last-in-first-out (LIFO) policy.
 * By tradition, we name the stack insert method push() and the stack remove 
 * operation pop(). We also include a method to test whether the stack is empty,
 * as indicated in the following API:
 * 
 * Implement the ArrayStackOfIntegers ADT using arrays. Look at the API on the website.
 * 
 * @David Chen
 * @Java 1.8.0 - 11/2/20
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStackOfIntegers_DC implements Iterable {
    private Integers[] items; // holds the items
    private int n; // number of items in stack

    public ArrayStackOfIntegers(int capacity) {
        items = (Integer[]) new Object[capacity];
    }

    public boolean isEmpty() {
        ----
    }

    public boolean isFull() {
        ----
    }

    public void push(Integer item) {
        ----
    }

    public Integer pop() {

    }

    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator {

    ----
    public void remove() 
    {}
    }
}
