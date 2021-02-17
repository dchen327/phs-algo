
/**
 * Part 2: binary search. When binary searching a sorted array that 
 * contains more than one key equal to the search key, the client may 
 * want to know the index of either the first or the last such key. 
 * Accordingly, implement the following API:
 * 
 * @David Chen
 * @Java 1.8.0 - 2/17/21
 */

import java.util.Comparator;

public class BinarySearchDeluxe {
    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = a.length - 1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            int c = comparator.compare(key, a[mid]);
            if (c < 0) {
                high = mid - 1;
            } else if (c > 0) {
                low = mid + 1;
            } else if (mid == low) { // first element
                return mid;
            } else {
                high = mid;
            }
        }
        return -1; // not found
    }

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        int low = 0, high = a.length - 1, mid;
        while (low <= high) {
            mid = (low + high + 1) / 2; // take right side of splits
            int c = comparator.compare(key, a[mid]);
            if (c < 0) {
                high = mid - 1;
            } else if (c > 0) {
                low = mid + 1;
            } else if (mid == high) { // last element
                return mid;
            } else {
                low = mid;
            }
        }
        return -1; // not found
    }

    // testing
    public static void main(String[] args) {
        Integer[] nums = { 1, 2, 3, 3, 3, 5, 6, 7, 8, 8, 10 };
        // use default comparator
        System.out.println(firstIndexOf(nums, 8, Comparator.<Integer>naturalOrder()));
        System.out.println(lastIndexOf(nums, 8, Comparator.<Integer>naturalOrder()));
    }
}
