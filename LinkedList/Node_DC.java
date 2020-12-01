public class Node_DC {
    private String value;
    private Node_DC nextNode;

    // create a head node with a value
    public Node_DC(String value) {
        this.value = value;
    }

    public Node_DC(String value, Node_DC nextNode) {
        this.nextNode = nextNode;
    }
}
