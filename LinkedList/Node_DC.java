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

public class Node_DC {
    private String value;
    private Node_DC nextNode;

    // create a head node with a value
    public Node_DC(String value) {
        this.value = value;
    }

    public Node_DC(String value, Node_DC nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node_DC getNext() {
        return this.nextNode;
    }

    public void setNext(Node_DC nextNode) {
        this.nextNode = nextNode;
    }

    public static void main(String[] args) {
        Node_DC head = new Node_DC("1st");
        Node_DC node2 = new Node_DC("2nd");
        head.setNext(node2);
        System.out.println(head.getNext().getValue());
    }
}
// the output
/**
2nd
 */