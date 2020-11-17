/**
 * Create a system to simulate vehicles at an intersection. Assume that there is one lane going
 *  in each of four directions, with stoplights facing each direction. Vary the arrival average
 *  of vehicles in each direction and the frequency of the light changes to view the “behavior” 
 * of the intersection. 
 * 
 * @David Chen
 * @Java 1.8.0 - 11/16/20
 */

public class Intersections_DC {
    public static void main(String[] args) {
        Queue_DC queue = new Queue_DC();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        System.out.println(queue);
    }
}
