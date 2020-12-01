/**
 * Create a Node class for a linked list
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
        System.out.println(head.getNext());
    }
}
