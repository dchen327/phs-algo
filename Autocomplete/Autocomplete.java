
/**
 * Part 3: autocomplete. In this part, you will implement an immutable data type that 
 * provides autocomplete functionality for a given set of string and weights, using Term 
 * and BinarySearchDeluxe. To do so, sort the terms in lexicographic order; use binary 
 * search to find the set of terms that start with a given prefix; and sort the matching 
 * terms in descending order by weight. Organize your program by creating an immutable 
 * data type Autocomplete with the following API:
 * 
 * Run like this:
 * `javac Autocomplete.java && java Autocomplete wiktionary.txt 5`
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
            String query = fileScan.nextLine().trim(); // read the next query
            terms[i] = new Term(query, weight); // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String prefix = scan.nextLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                System.out.println(results[i]);
        }
    }
}
// the output
/*
using wiktionary.txt with 5

auto
automobile | 619695.0
automatic | 424997.0
comp
company | 1.33159E7
complete | 7803980.0
companion | 6038490.0
completely | 5205030.0
comply | 4481770.0
the
the | 5.6271872E9
they | 3.340398E8
their | 2.820265E8
them | 2.509917E8
there | 1.9612E8
hello
hi
his | 8.799755E8
him | 3.971997E8
himself | 8.63478E7
high | 2.66672E7
history | 1.41958E7
pha
phase | 842716.0
phantom | 649837.0
phases | 530613.0
pho
photograph | 909488.0
photographs | 566927.0
phoebe | 448019.0
ab
about | 1.414687E8
above | 2.71641E7
able | 2.00145E7
absence | 5350280.0
absolutely | 5139440.0
st
still | 6.40067E7
stood | 3.08025E7
state | 2.77682E7
strong | 2.00224E7
story | 1.80998E7
ha
had | 6.139336E8
have | 4.3465E8
has | 1.602329E8
hand | 6.48227E7
having | 4.15355E7



using cities.txt with 3

Pr
Pretoria, South Africa | 1619438.0
Prague, Czech Republic | 1165581.0
Pristina, Kosovo | 550000.0
Prince
Prince George, British Columbia, Canada | 65558.0
Prince Albert, Saskatchewan, Canada | 34609.0
Prince Edward, Ontario, Canada | 25496.0
Princet
Princeton, Florida, United States | 22038.0
Princeton Meadows, New Jersey, United States | 13834.0
Princeton, New Jersey, United States | 12307.0
Bos
Boston, Massachusetts, United States | 617594.0
Boshan, China | 153596.0
Bossier City, Louisiana, United States | 61315.0
San
Santiago, Chile | 4837295.0
Santo Domingo, Dominican Republic | 2201941.0
Sanaa, Yemen | 1937451.0
San 
San Antonio, Texas, United States | 1327407.0
San Diego, California, United States | 1307402.0
San Jose, California, United States | 945942.0
M
Mumbai, India | 1.2691836E7
Mexico City, Distrito Federal, Mexico | 1.2294193E7
Manila, Philippines | 1.0444527E7
Al M
Al Maḩallah al Kubrá, Egypt | 431052.0
Al Manşūrah, Egypt | 420195.0
Al Mubarraz, Saudi Arabia | 290802.0
*/
