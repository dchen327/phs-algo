
/**
 * To identify an outcast, compute the sum of the distances between each noun and every other one
 * Assume that argument to outcast() contains only valid wordnet nouns (and that it contains at least two such nouns).
 * The following test client takes from the command line the name of a synset file, the name of a hypernym file, followed by the names of outcast files, 
 * and prints out an outcast in each file
 * 
 * @author David Chen and Joann Shi
 * @version Java 1.8.0 - 6/11/21
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private final WordNet wn;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		wn = wordnet;

	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		int max = 0;
		String finalOutcast = "";

		for (int i = 0; i < nouns.length; i++) {
			for (int j = i + 1; j < nouns.length; j++) {
				int distance = wn.distance(nouns[i], nouns[j]);
				if (distance > max) {
					max = distance;
					finalOutcast = nouns[j];
				}
			}
		}
		return finalOutcast;
	}

	// see test client below
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}

/**
% more outcast5.txt
horse zebra cat bear table

% more outcast8.txt
water soda bed orange_juice milk apple_juice tea coffee

% more outcast11.txt
apple pear peach banana lime lemon blueberry strawberry mango watermelon potato

% java Outcast synsets.txt hypernyms.txt outcast5.txt outcast8.txt outcast11.txt
outcast5.txt: table
outcast8.txt: bed
outcast11.txt: potato
*/
