
/**
 * Implement the ADT Set, Set_YI.java with the methods below. The Set ADT 
 * uses a LinkedList structure. ‌ In mathematics, a Set is a well-defined 
 * collection of distinct objects, considered as an object in its own
 * right. In this implementation of Set, the Set ADT behaves like a
 * LinkedList but it doesn’t allow duplicates. Create a Node class.
 * 
 * @David Chen
 * @Java 1.8.0 - 12/1/20
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;

public class Set_DC implements Iterable<String> {
    private Node_DC headNode;

    public void add(String s) { // add value to set
        if (headNode == null) { // first element, set to head
            headNode = new Node_DC(s);
        }
        if (headNode.getValue().equals(s)) { // value already present
            return;
        }
        // traverse to the end of the linked list
        Node_DC nextNode = headNode;
        while (nextNode.getNext() != null) {
            nextNode = nextNode.getNext();
            if (nextNode.getValue().equals(s)) { // value already present
                return;
            }
        }
        nextNode.setNext(new Node_DC(s));
    }

    public void addAll(ArrayList<String> values) { // add all values to set
        for (String value : values) {
            add(value);
        }
    }

    public void clear() { // effectively clear the set (this will cause memory leaks I think)
        headNode = null;
    }

    public boolean contains(String s) {
        for (String val : this) { // traverse set to search for s
            if (s.equals(val)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(ArrayList<String> values) { // check if all are present
        for (String value : values) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Set_DC compSet) { // check if equal to another set by making sure all elements in one set are present in the other
        if (size() != compSet.size()) {
            return false;
        }
        for (String value : this) {
            if (!compSet.contains(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() { // check if set is empty
        return headNode == null;
    }

    public boolean remove(String s) {
        if (headNode.getValue().equals(s)) { // check if head node is to be removed
            headNode = headNode.getNext();
            return true;
        }
        Node_DC currNode = headNode;
        while (currNode.getNext() != null) {
            if (currNode.getNext().getValue().equals(s)) { // remove next node
                currNode.setNext(currNode.getNext().getNext());
                return true;
            }
            currNode = currNode.getNext(); // continue traversal
        }
        return false;

    }

    public boolean removeAll(ArrayList<String> values) { // remove all values
        boolean removed = false; // return true if at least one value removed
        for (String value : values) {
            if (remove(value)) {
                removed = true;
            }
        }
        return removed;
    }

    public int size() {
        int t = 0;
        for (String s : this) {
            t++;
        }
        return t;
    }

    public Iterator<String> iterator() {
        return new SetIterator(headNode);
    }

    private class SetIterator implements Iterator<String> {
        private Node_DC currNode;

        public SetIterator(Node_DC headNode) {
            currNode = headNode;
        }

        public boolean hasNext() {
            return currNode != null;
        }

        public void remove() {

        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String currValue = currNode.getValue();
            currNode = currNode.getNext();
            return currValue;
        }
    }

    public static void main(String[] args) {
        // test all methods
        Set_DC set = new Set_DC();
        set.add("first");
        set.add("second");
        set.add("first");
        set.add("third");
        set.add("third");
        System.out.println("Did we remove second? " + set.remove("second"));
        System.out.println("Set contents:");
        for (String s : set) { // testing iteration
            System.out.println(s);
        }
        System.out.println("Size: " + set.size());
        System.out.println("Contains third? " + set.contains("third"));
        System.out.println("Contains eighth? " + set.contains("eighth"));
        // put some test values into an ArrayList
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("first", "second", "fourth", "fifth"));
        System.out.println("ArrayList: " + values);
        System.out.println("Set contains all values? " + set.containsAll(values));
        set.addAll(values);
        System.out.println("Set contains all values after adding all? " + set.containsAll(values));
        set.removeAll(values);
        System.out.println("Is set empty after removing all values? " + set.isEmpty());
        System.out.println("Does set still contain third? " + set.contains("third"));
        Set_DC set2 = new Set_DC();
        set2.add("third");
        System.out.println("Set equals new set? " + set.equals(set2));
        set.clear();
        System.out.println("Set equals new set after clearing set? " + set.equals(set2));
    }
}
// the output
/**
Did we remove second? true
Set contents:
first
third
Size: 2
Contains third? true
Contains eighth? false
ArrayList: [first, second, fourth, fifth]
Set contains all values? false
Set contains all values after adding all? true
Is set empty after removing all values? false
Does set still contain third? true
Set equals new set? true
Set equals new set after clearing set? false
 */