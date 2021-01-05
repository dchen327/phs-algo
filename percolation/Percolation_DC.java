
/**
 * Percolation. Given a composite system comprised of randomly distributed insulating
 *  and metallic materials: what fraction of the materials need to be metallic so
 *  that the composite system is an electrical conductor? Given a porous landscape 
 * with water on the surface (or oil below), under what conditions will the water 
 * be able to drain through to the bottom (or the oil to gush through to the surface)? 
 * Scientists have defined an abstract process known as percolation to model such situations.
 * 
 * @David Chen
 * @Java 1.8.0 - 12/18/20
 * 
 * Run with `javac-algs4 Percolation_DC.java && java-algs4 Percolation_DC`
 * after installing the stuff from algs4.jar
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation_DC {
    private boolean[][] grid; // false is closed
    private WeightedQuickUnionUF quickFind;
    private int N;

    // create N-by-N grid, with all sites initially blocked
    public Percolation_DC(int N) {
        this.N = N;
        grid = new boolean[N][N];
        quickFind = new WeightedQuickUnionUF(N * N + 2);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[row-1][col-1] = true;
        if (row == 1) {
            quickFind.union(getQFIndex)
        }   
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

    }

    // number of open sites
    public int numberOfOpenSites() {

    }

    // does the system percolate?
    public boolean percolates() {

    }

    // convert row and col to qf index
    private int quickFindIdx(int row, int col) {
        return (row - 1) * n + col;
    }

    // attempt to union if in bounds
    private void unionIfValid(int row1, int col1, int row2, int col2) {
        if (0 < row2 && row2 <= N && 0 < col2 && col2 <= N && isOpen(row2, col2)) {
            quickFind.union(quickFindIdx(row1, col1), quickFindIdx(row2, col2));
        }

    // unit testing (required)
    public static void main(String[] args) {

    }
}