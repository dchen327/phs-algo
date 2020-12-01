
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

public class Set_DC implements Iterable<String> {
    private Node_DC headNode;

    public void add(String s) {
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
        for (String s : set) {
            System.out.println(s);
        }
    }
}
