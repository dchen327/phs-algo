
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

import java.awt.Color;

public class PhotoMagic_DC {
    // Returns a transformed copy of the specified picture, using the specified LFSR.
    public static Picture transform(Picture picture, LFSR_DC lfsr) {
        int width = picture.width();
        int height = picture.height();
        Picture newPicture = new Picture(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int newR = r ^ lfsr.generate(8);
                int newG = g ^ lfsr.generate(8);
                int newB = b ^ lfsr.generate(8);
                Color newColor = new Color(newR, newG, newB);
                newPicture.set(col, row, newColor);
            }
        }
        return newPicture;
    }

    // Takes the name of an image file and
    // a description of an LFSR as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        String seed = args[1];
        int tap = Integer.parseInt(args[2]);
        LFSR_DC lfsr = new LFSR_DC(seed, tap);
        Picture newPicture = transform(picture, lfsr);
        newPicture.show();
    }
}
