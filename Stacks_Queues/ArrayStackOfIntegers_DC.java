
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
    private Integer[] items; // holds the items
    private int n; // number of items in stack and index of top element

    public ArrayStackOfIntegers_DC(int capacity) {
        items = new Integer[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == items.length;
    }

    public void push(Integer item) {
        if (!isFull()) {
            items[n] = item;
            n++;
        } else {
            System.out.println("Unable to push, stack is full.");
        }
    }

    public Integer pop() {
        n--;
        return items[n];
    }

    public String toString() {
        // print out stack from bottom to top
        String s = "";
        for (int i = 0; i < n; i++) {
            s += Integer.toString(items[i]) + " ";
        }
        return s;
    }

    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator {

        private int pos; // stores position in array

        public ReverseArrayIterator() {
            pos = n - 1; // start at the end of the array
        }

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[pos--];
        }

        @Override
        public void remove() {
        }
    }

    // tester code
    public static void main(String[] args) {
        ArrayStackOfIntegers_DC stack = new ArrayStackOfIntegers_DC(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Empty? " + stack.isEmpty());
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(100);
        System.out.println(stack);
        System.out.println("Full? " + stack.isFull());
        System.out.println("Testing iteration:");
        for (Object i : stack) {
            System.out.print(i + " ");
        }
    }
}

























// output
/*
1 2 3 
Pop: 3
Pop: 2
Pop: 1
Empty? true
Unable to push, stack is full.
5 4 3 2 1 
Full? true
Testing iteration:
1 2 3 4 5
 */