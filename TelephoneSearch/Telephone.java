
/**
 * Write a telephone lookup program, Telephone.java. Read the data set of 1,000 names and telephone numbers from the attached file.
 * It contains the numbers in random order. Handle lookups by name and also reverse lookups by phone number. 
 * Use Comparator and a binary search for both lookups.
 * 
 * @David Chen
 * @Java 1.8.0 - 2/10/21
 */

import java.io.*;

public class Telephone {
    public static void main(String[] args) {
        System.out.println(new File(".").getAbsoluteFile());
        FileReader reader = new FileReader("/home/dchen327/coding/phs-algo/TelephoneSearch/sample.txt");
        // BufferedReader reader = new BufferedReader(new FileReader("sample.txt")); // read in inputs
        // String line;
        // while ((line = reader.readLine()) != null) {
        //     String[] data = line.split(",");
        //     System.out.println(data[0]);
        // }
    }
}