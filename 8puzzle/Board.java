/**
 * Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.
 * 
 * The problem. The 8-puzzle problem (Links to an external site.) is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square. Your goal is to rearrange the blocks so that they are in order, using as few moves as possible. You are permitted to slide blocks horizontally or vertically into the blank square.
 * 
 * @author Joann Shi and David Chen
 * @version Java 1.8.0 - 5/8/21
 */
/*


*/

public class Board {
    private int[][] board;
    private int size;

    public Board(int[][] blocks) { // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        this.board = blocks;
        this.size = blocks.length;
    }

    public int size() { // board size N
        return size;
    }

    public int hamming() { // number of blocks out of place
        int r, c, hamming = 0;
        for (int i = 0; i < size * size; i++) {
            r = i / size; // convert line to row/col
            c = i % size;
            if (board[r][c] != 0 && board[r][c] != i + 1) {
                hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and goal
        int r1, c1, manhattan = 0;
        for (int i = 0; i < size * size; i++) {
            r1 = i / size; // convert line to row/col
            c1 = i % size;
            for (int r2 = 0; r2 < size; r2++) {
                for (int c2 = 0; c2 < size; c2++) {
                    if (i + 1 == board[r2][c2]) {
                        manhattan += Math.abs(r1 - r2) + Math.abs(c1 - c2);
                    }
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() { // is this board the goal board?
        int r, c;
        for (int i = 1; i < size * size; i++) {
            r = i / size; // convert line to row/col
            c = i % size;
            if (board[r][c] != i) {
                return false;
            }
        }
        return true;
    }

    public boolean isSolvable() { // is this board solvable?
        return true;
    }

    public boolean equals(Object y) { // does this board equal y?
        // compare string representations to check for equality (board is private)
        return y.toString().equals(toString());
    }

    // public Iterable<Board> neighbors() { // all neighboring boards

    // }

    public String toString() { // string representation of this board (in the output format specified below)
        String s = "";
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                s += board[r][c] + " ";
            }
            s += '\n';
        }
        return s;
    }

    public static void main(String[] args) { // unit tests (not graded)
        int[][] blocks = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 }, };
        Board board = new Board(blocks);
        System.out.println(board);
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
    }
}