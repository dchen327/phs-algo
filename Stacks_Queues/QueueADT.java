/**
 * A queue is a linear collection whose elements are added on one end and removed from the other.
 * Therefore, we say that queue elements are processed in a first in, first out (FIFO) manner.
 * Elements are removed from a queue in the same order in which they are placed on the queue.
 * 
 * Queue elements are processed in a FIFO manner—the first element in is the first element out.
 * A queue is a convenient collection for storing a repeating code key.
 * Simulations are often implemented using queues to represent waiting lines.
 * A linked implementation of a queue is facilitated by references to the first and last elements of the linked list.
 * The enqueue and dequeue operations work on opposite ends of the collec- tion.
 * Because queue operations modify both ends of the collection, fixing one end at index 0 requires that elements be shifted.
 * The shifting of elements in a noncircular array implementation creates an O(n) complexity.
 * Treating arrays as circular eliminates the need to shift elements in an array queue implementation.
 * 
 * @David Chen
 * @Java 1.8.0 - 11/16/20
 */

public interface QueueADT {
    // Adds one element to the rear of the queue
    public void enqueue(String element);

    // Removes and returns the element at the front of the queue
    public String dequeue();

    // Returns without removing the element at the front of the queue
    public String first();

    // Returns true if the queue contains no elements
    public boolean isEmpty();

    // Returns the number of elements in the queue
    public int size();

    // Returns a string representation of the queue
    public String toString();
}
