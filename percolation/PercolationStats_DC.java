
/**
 * Percolation. Given a composite system comprised of randomly distributed insulating
 *  and metallic materials: what fraction of the materials need to be metallic so
 *  that the composite system is an electrical conductor? Given a porous landscape 
 * with water on the surface (or oil below), under what conditions will the water 
 * be able to drain through to the bottom (or the oil to gush through to the surface)? 
 * Scientists have defined an abstract process known as percolation to model such situations.
 * 
 * To perform a series of computational experiments, create a data type PercolationStats with the following API.
 * 
 * public class PercolationStats {
   public PercolationStats(int N, int T)   // perform T independent experiments on an N-by-N grid
   public double mean()                    // sample mean of percolation threshold
   public double stddev()                  // sample standard deviation of percolation threshold
   public double confidenceLow()           // low  endpoint of 95% confidence interval
   public double confidenceHigh()          // high endpoint of 95% confidence interval
* }
 * 
 * @David Chen
 * @Java 1.8.0 - 12/18/20
 * 
 * Run with `javac-algs4 PercolationStats_DC.java && java-algs4 PercolationStats_DC <N> <T>`
 * after installing the stuff from algs4.jar
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats_DC {
    private int T;
    private Percolation_DC percolation;
    private double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats_DC(int N, int T) {
        this.T = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            percolation = new Percolation_DC(N);
            int numOpen = 0;
            while (!percolation.percolates()) { // keep looping
                int row = StdRandom.uniform(N) + 1; // base-1
                int col = StdRandom.uniform(N) + 1; // base-1
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    numOpen++;
                }
            }
            fractions[i] = numOpen * 1.0 / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats_DC percolationStats = new PercolationStats_DC(N, T);

        System.out.println("mean()                  = " + percolationStats.mean());
        System.out.println("stddev()                = " + percolationStats.stddev());
        System.out.println("confidenceLow()         = " + percolationStats.confidenceLow());
        System.out.println("confidenceHigh()        = " + percolationStats.confidenceHigh());
    }
}
// the output
/**
200, 100
mean()                  = 0.59382525
stddev()                = 0.007937182742407005
confidenceLow()         = 0.5922695621824882
confidenceHigh()        = 0.5953809378175118

2, 100000
mean()                  = 0.6670225
stddev()                = 0.11772530775597817
confidenceLow()         = 0.6662928309829237
confidenceHigh()        = 0.6677521690170762

8, 3000
mean()                  = 0.5953177083333333
stddev()                = 0.08273720958949979
confidenceLow()         = 0.5923569953122362
confidenceHigh()        = 0.5982784213544304

50, 1000
mean()                  = 0.591377199999999
stddev()                = 0.027389637317649766
confidenceLow()         = 0.5896795726910886
confidenceHigh()        = 0.5930748273089095
 */