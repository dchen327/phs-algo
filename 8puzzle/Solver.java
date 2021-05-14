import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.
 * 
 * The problem. The 8-puzzle problem (Links to an external site.) is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square. Your goal is to rearrange the blocks so that they are in order, using as few moves as possible. You are permitted to slide blocks horizontally or vertically into the blank square.
 * 
 * Run with `javac-algs4 Solver.java && java-algs4 Solver`
 * 
 * @author Joann Shi and David Chen
 * @version Java 1.8.0 - 5/8/21
 */

public class Solver {

	// priority queues for future board 
	private MinPQ<SearchNode> initPQ;
	private MinPQ<SearchNode> twinPQ;
	private boolean solvable; //if puzzle is solvable
	private SearchNode finalBoard; //the final board

	// wrap around a board and make it Comparable 
	private class SearchNode implements Comparable<SearchNode> {
		public Board board;
		public int moves;
		public SearchNode previous;

		public SearchNode(Board b) {
			board = b;
			moves = 0;
			previous = null;
		}

		public SearchNode(Board b, int mov, SearchNode prev) {
			board = b;
			moves = mov;
			previous = prev;
		}

		// implementing priority function 
		// distance + moves to reach current board state
		public int compareTo(SearchNode other) {
			int thisManhattan = this.board.manhattan() + this.moves;
			int otherManhattan = other.board.manhattan() + other.moves;

			if (thisManhattan < otherManhattan)
				return -1;
			if (thisManhattan > otherManhattan)
				return 1;

			return 0;
		}

	}

	// find a solution to the initial board (using the A* algorithm)

	// used twinPQ to see if input board is solvable (twin method in Board)
	// twin configuration has two adjacent row elements swapped
	public Solver(Board initial) {
		solvable = false;

		initPQ = new MinPQ<SearchNode>();
		twinPQ = new MinPQ<SearchNode>();

		initPQ.insert(new SearchNode(initial, 0, null));
		twinPQ.insert(new SearchNode(initial.twin(), 0, null));

		SearchNode initSN;
		SearchNode twinSN;

		while (true) {
			initSN = initPQ.delMin();
			twinSN = twinPQ.delMin();

			if (initSN.board.isGoal()) {
				finalBoard = initSN;
				solvable = true;
				break;
			}
			if (twinSN.board.isGoal()) {
				finalBoard = twinSN;
				solvable = false;
				break;
			}

		}
		for (Board initBoard : initSN.board.neighbors()) {
			if (initSN.previous == null || !initBoard.equals(initSN.previous.board))
				initPQ.insert(new SearchNode(initBoard, initSN.moves + 1, initSN));
		}
		for (Board twinBoard : twinSN.board.neighbors()) {
			if (twinSN.previous == null || !twinBoard.equals(twinSN.previous.board))
				twinPQ.insert(new SearchNode(twinBoard, twinSN.moves + 1, twinSN));
		}
	}

	// min number of moves to solve initial board
	// return -1 if board isn't solvable
	public int moves() {
		if (this.solvable) {
			return finalBoard.moves;
		}

		return -1;

	}

	// sequence of boards in a shortest solution
	public Iterable<Board> solution() {
		if (this.solvable) {
			Stack<Board> stack = new Stack<Board>();
			SearchNode temp = finalBoard;

			while (temp != null) {
				stack.push(temp.board);
				temp = temp.previous;
			}
			return stack;
		}
		return null;
	}

	// whether or not the input board was solvable
	public boolean isSolvable() {
		return solvable;
	}

	// solve a slider puzzle (given below) 
	public static void main(String[] args) {
		// create initial board from file
		//	    In in = new In(args[0]);
		//	    int N = in.readInt();
		//	    int[][] blocks = new int[N][N];
		//	    for (int i = 0; i < N; i++)
		//	        for (int j = 0; j < N; j++)
		//	            blocks[i][j] = in.readInt();
		//	    Board initial = new Board(blocks);

		int[][] blocks = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 }, };
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
}
