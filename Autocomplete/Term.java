
/**
 * Part 1: autocomplete term. Write an immutable data type Term.java that represents
 * an autocomplete term: a string query and an associated real-valued weight. 
 * You must implement the following API, which supports comparing terms by three 
 * different orders: lexicographic order by query string (the natural order); 
 * in descending order by weight (an alternate order); and lexicographic order 
 * by query string but using only the first r characters (a family of alternate orderings). 
 * The last order may seem a bit odd, but you will use it in Part 3 to find all 
 * terms that start with a given prefix (of length r).
 * 
 * @David Chen
 * @Java 1.8.0 - 2/17/21
 */

import java.util.Comparator;

public class Term implements Comparable<Term> {
    // query + weight = autocomplete term
    private String query;
    private double weight;

    // Initialize a term with the given query string and weight.
    public Term(String query, double weight) {
        if (query == null || weight < 0)
            throw new IllegalArgumentException(); // as per instructions

        this.query = query;
        this.weight = weight;
    }

    // Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        Comparator reverseWeightOrder = new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                return -Double.compare(t1.weight, t2.weight); // use default, but negate
            }
        };
        return reverseWeightOrder;
    }

    // Compare the terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) { // can't have negative r
            throw new IllegalArgumentException();
        }
        Comparator prefixOrder = new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                int l1 = t1.query.length();
                int l2 = t2.query.length();
                for (int i = 0; i < r; i++) {
                    if (i >= l1 || i >= l2) {
                        break; // can't keep comparing
                    }
                    if (t1.query.charAt(i) < t2.query.charAt(i)) {
                        return -1;
                    } else if (t1.query.charAt(i) > t2.query.charAt(i)) {
                        return 1;
                    }
                }

                if (l1 < l2 && l1 < r) {
                    return -1; // shorter one is less
                } else if (l1 > l2 && l2 < r) {
                    return 1; // longer one is greater
                } else {
                    return 0; // tie
                }
            }
        };
        return prefixOrder;
    }

    // Compare the terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return query + " | " + weight;
    }

    // testing
    public static void main(String[] args) {
        Term t1 = new Term("Hello World", 3);
        Term t2 = new Term("Hello Algorithms", 8);
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t1.compareTo(t2)); // should be > 0
        System.out.println(byReverseWeightOrder().compare(t1, t2)); // 1
        System.out.println(byPrefixOrder(8).compare(t1, t2)); // 1
    }
}

// the output
/*
Hello World | 3.0
Hello Algorithms | 8.0
22
1
1
*/