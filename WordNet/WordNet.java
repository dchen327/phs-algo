
/**
 * WordNet is a semantic lexicon for the English language that is used extensively by computational linguists and cognitive scientists; for example, it was a key component in IBM's Watson. WordNet groups words into sets of synonyms called synsets and describes semantic relationships between them. One such relationship is the is-a relationship, which connects a hyponym (more specific synset) to a hypernym (more general synset). For example, a plant organ is a hypernym of carrot and plant organ is a hypernym of plant root.

The WordNet digraph. Your first task is to build the wordnet digraph: each vertex v is an integer that represents a synset, and each directed edge v→w represents that w is a hypernym of v. The wordnet digraph is a rooted DAG: it is acyclic and has one vertex—the root—that is an ancestor of every other vertex. However, it is not necessarily a tree because a synset can have more than one hypernym. A small subgraph of the wordnet digraph is illustrated below.

Run with `javac-algs4 WordNet.java && java-algs4 WordNet`
 * 
 * @author David Chen and Joann Shi
 * @version Java 1.8.0 - 6/11/21
 * 
 */

import java.util.HashMap;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Digraph wordNet;
    private HashMap<Integer, String> synsets;
    private HashMap<String, ArrayList<Integer>> synsetToID;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynsets(synsets);
        readHypernyms(hypernyms);
        wordNet = new Digraph(this.synsets.size());
        sap = new SAP(wordNet);
    }

    // read synsets from file
    private void readSynsets(String synsetFile) {
        synsets = new HashMap<Integer, String>();
        synsetToID = new HashMap<String, ArrayList<Integer>>();
        In in = new In(synsetFile);

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            synsets.put(id, parts[1]);
            String[] nouns = parts[1].split(" ");
            for (String noun : nouns) {
                if (!synsets.containsKey(noun)) {
                    synsetToID.put(noun, new ArrayList<>());
                }
                synsetToID.get(noun).add(id);
            }
        }
    }

    // read hypernyms from file
    private void readHypernyms(String hypernyms) {
        Digraph g = new Digraph(synsetToID.size());

        In in = new In(hypernyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            int synset = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                int hypernym = Integer.parseInt(parts[i]);
                wordNet.addEdge(synset, hypernym);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsetToID.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synsets.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return sap.length(synsetToID.get(nounA), synsetToID.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        ArrayList<Integer> v = synsetToID.get(nounA);
        ArrayList<Integer> w = synsetToID.get(nounB);
        return synsets.get(sap.ancestor(v, w));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        // unit testing in SAP.java
    }
}