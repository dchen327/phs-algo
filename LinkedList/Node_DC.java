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

    public Node_DC getValue() {
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
}
