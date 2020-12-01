
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
        Set_DC set = new Set_DC();
        set.add("first");
        set.add("second");
        set.add("first");
        set.add("third");
        System.out.println(set.remove("second"));
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println(set.size());
        // System.out.println(set.contains("second"));
    }
}
