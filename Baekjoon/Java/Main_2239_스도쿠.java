import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 2239 스도쿠

class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static StringTokenizer st = null;

	static boolean answered;
	static int[][] sudoku = new int[9][9];
	static int[][] answer = new int[9][9];

	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = (int) (str.charAt(j) - '0');
			}
		}

		setSudoku(0, 0);
	}

	public static void setSudoku(int x, int y) {
		if (answered)
			return;

		if (x == 8 && y == 9) {
			answered = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			return;
		}

		if (y >= 9) {
			x++;
			y -= 9;
		}

		if (sudoku[x][y] != 0) {
			setSudoku(x, y + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				sudoku[x][y] = i;
				if (checkArea(sudoku, x, y)) {
					setSudoku(x, y + 1);
				}
				sudoku[x][y] = 0;
			}
		}
	}

	public static boolean checkArea(int[][] grid, int x, int y) {
		int px = (x / 3) * 3;
		int py = (y / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (px + i == x && py + j == y)
					continue;
				if (grid[px + i][py + j] == grid[x][y])
					return false;
			}
		}

		for (int i = 0; i < 9; i++) { // (x, i)
			if (i == y)
				continue;
			if (grid[x][i] == grid[x][y])
				return false;
		}
		for (int i = 0; i < 9; i++) { // (i, y)
			if (i == x)
				continue;
			if (grid[i][y] == grid[x][y])
				return false;
		}

		return true;
	}

}