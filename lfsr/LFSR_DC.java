/**
 * A linear-feedback shift register (LFSR) is a register of bits that performs
 * discrete step operations that - shifts the bits one position to the left and
 * - replaces the vacated bit by the exclusive or (xor) of the bit shifted off
 * and the bit previously at a given tap position in the register.
 * 
 * A LFSR has three parameters that characterize the sequence of bits it
 * produces: the number of bits n, the initial seed (the sequence of bits that
 * initializes the register), and the tap position tap. As in the example in
 * Lecture 0, the following illustrates one step of an 11-bit LFSR with initial
 * seed 01101000010 and tap positions 9.
 * 
 * Write a program that produces pseudo-random bits by simulating a
 * linear-feedback shift register, and then use it to implement a simple form of
 * encryption for digital pictures.
 * 
 * @David Chen
 * @Java 1.8.0 - 10/6/20
 */
public class LFSR_DC {
    private int[] bits;
    private int tap;

    // creates an LFSR with the specified seed and tap
    public LFSR_DC(String seed, int tap) {
        // entry i in bits corresponds to bit i
        // bit 1 is the rightmost bit
        bits = new int[seed.length() + 1]; // we're gonna ignore this array's first value
        for (int i = 0; i < seed.length(); i++) {
            bits[i + 1] = Character.getNumericValue(seed.charAt(seed.length() - 1 - i));
        }
        this.tap = tap;
    }

    // returns the number of bits in the LFSR.
    public int length() {
        return bits.length - 1;
    }

    // returns bit i as 0 or 1.
    public int bitAt(int i) {
        return bits[i];
    }

    // returns a string representation of this LFSR
    public String toString() {
        String output = "";
        // add bits in reverse order since we're storing from right to left
        for (int i = bits.length - 1; i > 0; i--) {
            output += Integer.toString(bits[i]);
        }
        return output;
    }

    // simulates one step of this LFSR and return the new bit as 0 or 1
    public int step() {
        int replaceBit = bits[bits.length - 1] ^ bits[tap];
        for (int i = bits.length - 1; i > 1; i--) {
            bits[i] = bits[i - 1];
        }
        bits[1] = replaceBit;
        return bits[1]; // return rightmost bit
    }

    // simulates k steps of this LFSR and return the k bits as a k-bit integer
    public int generate(int k) {
        int kbit = 0; // store results from k step simulations
        for (int step_num = 0; step_num < k; step_num++) {
            int bit = step();
            kbit = (kbit << 1) + bit; // shift kbit over 1, add new bit
            // equivalent to multiply by 2, add new bit
        }
        return kbit;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        StdOut.println("Test 0");
        LFSR_DC lfsr0 = new LFSR_DC("01101000010", 9);
        StdOut.println(lfsr0);
        StdOut.println("Test 1");
        LFSR_DC lfsr1 = new LFSR_DC("01101000010", 9);
        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }
        StdOut.println("Test 2");
        LFSR_DC lfsr2 = new LFSR_DC("01101000010", 9);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }
    }
}

// output
/*
Test 0
01101000010
Test 1
01101000010
11010000101 1
10100001011 1
01000010110 0
10000101100 0
00001011001 1
00010110010 0
00101100100 0
01011001001 1
10110010010 0
01100100100 0
Test 2
01101000010
00001011001 25
01100100100 4
10010011110 30
01111011011 27
01101110010 18
11001011010 26
01101011100 28
01110011000 24
01100010111 23
01011111101 29
*/