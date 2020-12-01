
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

public class Set_DC {
    private Node_DC headNode;

    public void add(String s) {
        if (headNode == null) { // first element, set to head
            headNode = new Node_DC(s);
        }
        System.out.println(headNode);
    }

    public static void main(String[] args) {
        Set_DC set = new Set_DC();
        System.out.println(set);
        set.add("first");
    }
}
