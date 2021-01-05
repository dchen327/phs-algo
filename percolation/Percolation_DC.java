
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
    private int top = 0, bottom;
    private int numOpen;

    // create N-by-N grid, with all sites initially blocked
    public Percolation_DC(int N) {
        this.N = N;
        grid = new boolean[N][N];
        quickFind = new WeightedQuickUnionUF(N * N + 2);
        bottom = N * N + 1;
        numOpen = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[row - 1][col - 1] = true;
        numOpen++;
        if (row == 1) {
            quickFind.union(quickFindIdx(row, col), top);
        }
        if (row == N) {
            quickFind.union(quickFindIdx(row, col), bottom);
        }
        unionIfValid(row, col, row - 1, col); // up
        unionIfValid(row, col, row + 1, col); // down        
        unionIfValid(row, col, row, col - 1); // left
        unionIfValid(row, col, row, col + 1); // right
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return quickFind.connected(top, quickFindIdx(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickFind.connected(top, bottom);
    }

    // convert row and col to qf index
    private int quickFindIdx(int row, int col) {
        return (row - 1) * N + col;
    }

    // attempt to union if in bounds
    private void unionIfValid(int row1, int col1, int row2, int col2) {
        if (0 < row2 && row2 <= N && 0 < col2 && col2 <= N && isOpen(row2, col2)) {
            quickFind.union(quickFindIdx(row1, col1), quickFindIdx(row2, col2));
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation_DC percolation = new Percolation_DC(5);
        percolation.open(2, 3); // test opening
        System.out.println("Opened (2, 3)");
        System.out.println("Is (2, 3) open?: " + percolation.isOpen(2, 3));
        percolation.open(2, 4); // test opening
        percolation.open(3, 4); // test opening
        System.out.println("Total opened sites: " + percolation.numberOfOpenSites());
        System.out.println("Percolates? " + percolation.percolates());
        System.out.println("Is (3, 5) full? " + percolation.isFull(3, 5));
    }
}

// the output
