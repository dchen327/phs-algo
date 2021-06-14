
/**
 * Shortest ancestral path. An ancestral path between two vertices v and w in a digraph is a directed path from v to a common ancestor x, together with a directed path from w to the same ancestor x. A shortest ancestral path is an ancestral path of minimum total length. For example, in the digraph at left (digraph1.txt), the shortest ancestral path between 3 and 11 has length 4 (with common ancestor 1). In the digraph at right (digraph2.txt), one ancestral path between 1 and 5 has length 4 (with common ancestor 5), but the shortest ancestral path has length 2 (with common ancestor 0).

Run with `javac-algs4 WordNet.java && java-algs4 WordNet`
 * 
 * @author David Chen and Joann Shi
 * @version Java 1.8.0 - 6/11/21
 * 
 */

import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.DirectedCycle;

public class SAP {
    private Digraph graph;
    private final HashMap<HashSet<Integer>, int[]> cache; // hashset so order (v, w) doesn't matter

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = new Digraph(G);
        cache = new HashMap<>();
    }

    // is the digraph a directed acyclic graph?
    public boolean isDAG() {
        DirectedCycle test = new DirectedCycle(graph);
        return !test.hasCycle(); // directed cycle means it isn't a DAG
    }

    // // is the digraph a rooted DAG?
    // public boolean isRootedDAG() {
    // TODO
    // }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        shortestAncestralPath(v, w);
        HashSet<Integer> key = new HashSet<>();
        key.add(v);
        key.add(w);
        int[] val = cache.get(key);
        return val[0]; // first val is the length
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        shortestAncestralPath(v, w);
        HashSet<Integer> key = new HashSet<>();
        key.add(v);
        key.add(w);
        int[] val = cache.get(key);
        return val[1]; // second val is the length
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    // public int length(Iterable<Integer> v, Iterable<Integer> w) {
    // }

    // // a common ancestor that participates in shortest ancestral path; -1 if no such path
    // public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    // }

    private void shortestAncestralPath(int v, int w) {
        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(graph, w);

        HashSet<Integer> key = new HashSet<>();
        key.add(v);
        key.add(w);
        if (cache.containsKey(key)) // no need to recalculate
            return;

        int dist = 1000000000;
        int ancestor = -1;

        for (int vertexID = 0; vertexID < graph.V(); vertexID++) {
            if (vBFS.hasPathTo(vertexID) && vBFS.distTo(vertexID) < dist && wBFS.hasPathTo(vertexID)
                    && wBFS.distTo(vertexID) < dist) {
                int combinedDist = vBFS.distTo(vertexID) + wBFS.distTo(vertexID);
                if (combinedDist < dist) {
                    dist = combinedDist;
                    ancestor = vertexID;
                }
            }
        }

        if (dist == 1000000000) { // no path found
            dist = -1;
            ancestor = -1;
        }

        int[] val = new int[] { dist, ancestor };
        cache.put(key, val);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
