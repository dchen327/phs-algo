/******************************************************************************
 *  Compilation:  javac TestBST.java
 *  Execution:    java TestBST
 *  Dependencies: BST.java
 *
 *  A test client for BST.java.
 * 
 *  % java -ea TestBST
 *
 ******************************************************************************/

public class TestBST {
    public static void main(String[] args) { 
        String test = "S E A R C H E X A M P L E"; 
        String[] keys = test.split(" "); 
        int n = keys.length; 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; i < n; i++) 
            st.put(keys[i], i); 

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();


        // print keys in order using allKeys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

        // print keys in order using select
        StdOut.println("Testing select");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            StdOut.println(i + " " + st.select(i)); 
        StdOut.println();

        // test rank, floor, ceiling
        StdOut.println("key rank floor flor2 ceil");
        StdOut.println("-------------------------");
        for (char i = 'A'; i <= 'X'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s %4s\n", s, st.rank(s), st.floor(s), st.floor2(s), st.ceiling(s));
        }
        StdOut.println();

        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        StdOut.println("range search");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
        }
        StdOut.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        StdOut.println("After deleting the remaining keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

        StdOut.println("After adding back the keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++) 
            st.put(keys[i], i); 
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
        StdOut.println();

    }
}

// the output
/*
size = 10
min  = A
max  = X

Testing keys()
--------------------------------
A 8
C 4
E 12
H 5
L 11
M 9
P 10
R 3
S 0
X 7

Testing select
--------------------------------
0 A
1 C
2 E
3 H
4 L
5 M
6 P
7 R
8 S
9 X

key rank floor flor2 ceil
-------------------------
 A    0    A    A    A
 B    1    A    A    C
 C    1    C    C    C
 D    2    C    C    E
 E    2    E    E    E
 F    3    E    E    H
 G    3    E    E    H
 H    3    H    H    H
 I    4    H    H    L
 J    4    H    H    L
 K    4    H    H    L
 L    4    L    L    L
 M    5    M    M    M
 N    6    M    M    P
 O    6    M    M    P
 P    6    P    P    P
 Q    7    P    P    R
 R    7    R    R    R
 S    8    S    S    S
 T    9    S    S    X
 U    9    S    S    X
 V    9    S    S    X
 W    9    S    S    X
 X    9    X    X    X

range search
-------------------
A-Z (10) : A C E H L M P R S X 
Z-A ( 0) : 
X-X ( 1) : X 
0-Z (10) : A C E H L M P R S X 
B-G ( 2) : C E 
C-L ( 4) : C E H L 

After deleting the smallest 3 keys
--------------------------------
H 5
L 11
M 9
P 10
R 3
S 0
X 7

After deleting the remaining keys
--------------------------------

After adding back the keys
--------------------------------
A 8
C 4
E 12
H 5
L 11
M 9
P 10
R 3
S 0
X 7
*/