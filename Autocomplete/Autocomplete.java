
/**
 * Part 3: autocomplete. In this part, you will implement an immutable data type that 
 * provides autocomplete functionality for a given set of string and weights, using Term 
 * and BinarySearchDeluxe. To do so, sort the terms in lexicographic order; use binary 
 * search to find the set of terms that start with a given prefix; and sort the matching 
 * terms in descending order by weight. Organize your program by creating an immutable 
 * data type Autocomplete with the following API:
 * 
 * @David Chen
 * @Java 1.8.0 - 2/17/21
 */
import java.io.*;
import java.util.*;

public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException();
        }
        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms); // sorting
    }

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        int r = prefix.length();
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(r));
        if (firstIndex < 0) { // not found, return
            return new Term[0];
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(r));
        Term[] matches = new Term[1 + lastIndex - firstIndex];
        for (int i = 0; i < matches.length; i++) {
            matches[i] = terms[firstIndex + i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        int r = prefix.length();
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(r));
        if (firstIndex < 0) { // not found, return
            return 0;
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(r));
        return lastIndex - firstIndex + 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // read in the terms from a file
        String filename = args[0];
        Scanner fileScan = new Scanner(new File(filename));
        int N = fileScan.nextInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            double weight = fileScan.nextDouble(); // read the next weight
            String query = fileScan.next(); // read the next query
            terms[i] = new Term(query, weight); // construct the term
        }
        System.out.println(terms[10]);

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        // Autocomplete autocomplete = new Autocomplete(terms);
        // Scanner scan = new Scanner(System.in);
        // while (scan.hasNextLine()) {
        //     String prefix = scan.readLine();
        //     Term[] results = autocomplete.allMatches(prefix);
        //     for (int i = 0; i < Math.min(k, results.length); i++)
        //         System.out.println(results[i]);
        // }
    }
}
