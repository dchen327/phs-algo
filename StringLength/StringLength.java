
/**
 * Write a program, StringLength.java to sort an array list of strings by increasing length. Supply a Comparator.
 * 
 * @David Chen
 * @Java 1.8.0 - 2/10/21
 */

import java.io.*;
import java.util.*;

public class StringLength {
    public static void main(String[] args) throws IOException {
        ArrayList<String> names = new ArrayList<String>();
        Scanner scan = new Scanner(new File("samplenames.txt"));
        while (scan.hasNext()) {
            names.add(scan.next());
        }

        // custom length comparator
        Comparator lengthComparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        // sort with comparator
        Collections.sort(names, lengthComparator);
        System.out.println("Index 0: " + names.get(0));
        System.out.println("Index 5000: " + names.get(5000));
        System.out.println("Index 10000: " + names.get(10000));
        System.out.println("Index 15000: " + names.get(15000));
        System.out.println("Index 18000: " + names.get(18000));

        // print out all the names
        // for (String name : names) {
        //     System.out.println(name);
        // }
    }
}
// output
// we see that for larger indices, the names are longer
/**
Index 0: Ijob
Index 5000: Aserv
Index 10000: Gxvuoju
Index 15000: Zmbxrmjgz
Index 18000: Pfscsuakan
 */
