/**
 * Create a system to simulate vehicles at an intersection. Assume that there is one lane going
 * in each of four directions, with stoplights facing each direction. Vary the arrival average
 * of vehicles in each direction and the frequency of the light changes to view the “behavior” 
 * of the intersection. 
 * 
 * @David Chen
 * @Java 1.8.0 - 11/16/20
 */

public class Intersections_DC {
    private static Queue_DC north;
    private static Queue_DC south;
    private static Queue_DC east;
    private static Queue_DC west;
    private static Queue_DC lanes[];

    public static void main(String[] args) {
        north = new Queue_DC(); // pairs with south
        south = new Queue_DC();
        east = new Queue_DC(); // pairs with east
        west = new Queue_DC();
        lanes = new Queue_DC[] { north, south, east, west };

        int maxCars = 5;
        int maxLightChangeTime = 10;
        System.out.println("Max cars: " + maxCars + ", max light change time: " + maxLightChangeTime);
        for (int t = 0; t < 10; t++) {
            System.out.println(simulateTraffic(maxCars, maxLightChangeTime));
        }
    }

    // given the maximum number of cars coming in to the four lanes, as well
    // as the max time between light changes, simulate traffic (2 light changes) and print
    public static String simulateTraffic(int maxCars, int maxLightChangeTime) {

        for (int laneNum = 0; laneNum < 4; laneNum++) {
            // random number of cars that'll come to each lane
            int numCars = (int) (Math.random() * maxCars) + 1;
            // add cars to lane
            for (int carIdx = 0; carIdx < numCars; carIdx++) {
                lanes[laneNum].enqueue("car");
            }
        }

        // let North/South go first
        int lightTime = (int) (Math.random() * maxLightChangeTime) + 3;
        for (int t = 0; t < lightTime; t++) {
            if (!north.isEmpty()) {
                north.dequeue();
            }
            if (!south.isEmpty()) {
                south.dequeue();
            }
        }

        // let East/West go next
        lightTime = (int) (Math.random() * maxLightChangeTime) + 3;
        for (int t = 0; t < lightTime; t++) {
            if (!east.isEmpty()) {
                east.dequeue();
            }
            if (!west.isEmpty()) {
                west.dequeue();
            }
        }

        String s = "";
        for (int laneNum = 0; laneNum < 4; laneNum++) {
            s += "Lane " + (laneNum + 1) + ": " + lanes[laneNum].size() + "\n";
        }

        return s;
    }
}

// the output
/**
Max cars: 20, max light change time: 20
Lane 1: 0
Lane 2: 0
Lane 3: 0
Lane 4: 0

Lane 1: 14
Lane 2: 13
Lane 3: 16
Lane 4: 0

Lane 1: 4
Lane 2: 1
Lane 3: 15
Lane 4: 0

Lane 1: 6
Lane 2: 0
Lane 3: 7
Lane 4: 0

Lane 1: 2
Lane 2: 0
Lane 3: 10
Lane 4: 8

Lane 1: 3
Lane 2: 0
Lane 3: 10
Lane 4: 0

Lane 1: 0
Lane 2: 5
Lane 3: 8
Lane 4: 0

Lane 1: 0
Lane 2: 3
Lane 3: 10
Lane 4: 2

Lane 1: 10
Lane 2: 14
Lane 3: 1
Lane 4: 0

Lane 1: 6
Lane 2: 8
Lane 3: 3
Lane 4: 0






Simulating high traffic:
Max cars: 30, max light change time: 5
Lane 1: 27
Lane 2: 15
Lane 3: 6
Lane 4: 4

Lane 1: 32
Lane 2: 36
Lane 3: 18
Lane 4: 21

Lane 1: 44
Lane 2: 46
Lane 3: 14
Lane 4: 33

Lane 1: 57
Lane 2: 44
Lane 3: 23
Lane 4: 31

Lane 1: 56
Lane 2: 40
Lane 3: 25
Lane 4: 48

Lane 1: 54
Lane 2: 65
Lane 3: 32
Lane 4: 48

Lane 1: 68
Lane 2: 77
Lane 3: 27
Lane 4: 57

Lane 1: 74
Lane 2: 75
Lane 3: 49
Lane 4: 67

Lane 1: 89
Lane 2: 87
Lane 3: 62
Lane 4: 67

Lane 1: 88
Lane 2: 89
Lane 3: 77
Lane 4: 92




Simulating low traffic:

 */