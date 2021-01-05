
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
 * Run with `javac-algs4 Percolation_DC.java && java-algs4 Percolation_DC`
 * after installing the stuff from algs4.jar
 */

public class PercolationStats_DC {

}
