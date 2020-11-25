/**
 * Create a system using a stack and a queue to test whether a given string is a
 * palindrome (i.e., the characters read the same forward or backward).
 * 
 * @David Chen
 * @Java 1.8.0 - 11/25/20
 */
public class QSPalindrome_DC {
    public boolean isPalindrome(String s) {
        ArrayStackOfIntegers_DC stack = new ArrayStackOfIntegers_DC(1000);
        Queue_DC queue = new Queue_DC();
        for (int i = 0; i < s.length(); i++) {
            String value = s.substring(i, i + 1);
            int digit = Integer.parseInt(value); // ArrayStackOfIntegers only takes ints
            queue.enqueue(value); // add string to queue
            stack.push(digit); // add int to stack
        }
        // simultaneously pop from stack and dequeue from queue
        // thus, we compare the first element to the last, the second to the second to last, etc.
        // if the values aren't equal, return false
        // return true if we haven't returned false
        for (int i = 0; i < s.length(); i++) {
            String value = queue.dequeue();
            int digit = stack.pop();
            if (Integer.parseInt(value) != digit) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // tester code for palindromes
        QSPalindrome_DC palindromeChecker = new QSPalindrome_DC();
        String s = "0123210";
        System.out.println("Is " + s + " a palindrome? " + palindromeChecker.isPalindrome(s));
        s = "0001238090918320";
        System.out.println("Is " + s + " a palindrome? " + palindromeChecker.isPalindrome(s));
        s = "08000131711713100080";
        System.out.println("Is " + s + " a palindrome? " + palindromeChecker.isPalindrome(s));
    }
}
// the output
/**
Is 0123210 a palindrome? true
Is 0001238090918320 a palindrome? false
Is 08000131711713100080 a palindrome? true
 */