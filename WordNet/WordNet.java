
/**
 * WordNet is a semantic lexicon for the English language that is used extensively by computational linguists and cognitive scientists; for example, it was a key component in IBM's Watson. WordNet groups words into sets of synonyms called synsets and describes semantic relationships between them. One such relationship is the is-a relationship, which connects a hyponym (more specific synset) to a hypernym (more general synset). For example, a plant organ is a hypernym of carrot and plant organ is a hypernym of plant root.

The WordNet digraph. Your first task is to build the wordnet digraph: each vertex v is an integer that represents a synset, and each directed edge v→w represents that w is a hypernym of v. The wordnet digraph is a rooted DAG: it is acyclic and has one vertex—the root—that is an ancestor of every other vertex. However, it is not necessarily a tree because a synset can have more than one hypernym. A small subgraph of the wordnet digraph is illustrated below.
 * 
 * @author David Chen
 * @version Java 1.8.0 - 6/11/21
 * 
 */

public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {

    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {

    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}