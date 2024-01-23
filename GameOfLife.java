public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		test3(fileName, 4);	}
	

	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);   }
	
	private static void test2(String fileName) {
		int[][] board = read(fileName);   }
		
	
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);  }                  }

	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
			System.out.println();
			print(board);	}                  }

	public static int[][] read(String fileName) {
		In in = new In(fileName);
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows + 2][cols + 2];
		int n = 0;
		int row = 1;
		while (!in.isEmpty() && n < 100) {
			for (int i = 2; i < cols; i++) {
				String str = in.readLine();
				int ind = 1;
				if (str != null) {
					while (ind < str.length()) {						
			     if (str.charAt(ind) == 'x') {
							board[row][ind+1] = 1; } 
	                        else {
							board[row][ind+1] = 0;  }
						ind++; }
							
						row++;
				} 
				
				else {row++;}
				
			}     n++;   }			
		return board;
	}
	

	public static int[][] evolve(int[][] board) {
		int rows = board.length;
		int cols = board[0].length;
		int[][] tempBoard = new int[rows][cols];
		for (int i = 1; i < rows-1; i++) {
			for (int j = 1; j < cols-1; j++) {
				tempBoard[i][j] = cellValue(board, i, j);
			
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = tempBoard[i][j];
			}
		}
		return board;
	}

	public static int cellValue(int[][] board, int i, int j) {
	
		if (board[i][j] == 1) {
			if (count(board, i, j) < 2 || count(board, i, j) > 3) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (count(board, i, j) == 3) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	

	public static int count(int[][] board, int i, int j) {
		int aliveNeigh = 0;
		for (int k = (-1); k <= 1; k++) {
			for (int l = (-1); l <= 1; l++) {
				if (k != 0 || l != 0) {
				
					if (board[i-k][j-l] == 1) {
						aliveNeigh++;
					}	}	}}
		return aliveNeigh;   }

    public static void print(int[][] arr) {
		for (int i = 1; i < arr.length-1; i++) {
			for (int j = 1; j < arr[0].length-1; j++) {
				System.out.printf("%3s", arr[i][j]);
			}    System.out.println();
		}
	}
	
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		
		StdDraw.enableDoubleBuffering();
		
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}   }
