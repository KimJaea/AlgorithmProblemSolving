import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 2477 참외밭

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int C = Integer.parseInt(br.readLine());

		int[] dir = new int[5];
		int[][] grid = new int[6][2];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			// 1: right 2: left 3: down 4: up
			grid[i][0] = Integer.parseInt(st.nextToken());
			dir[grid[i][0]]++;
			grid[i][1] = Integer.parseInt(st.nextToken());
		}

		if (dir[1] == 2) {
			if (dir[3] == 2) { // ┒
				for (int i = 0; i < 6; i++) {
					if (grid[i][0] == 4) {
						System.out.print(C * getAnswer(i, grid));
					}
				}
			} else { // ┏
				for (int i = 0; i < 6; i++) {
					if (grid[i][0] == 2) {
						System.out.print(C * getAnswer(i, grid));
					}
				}
			}
		} else {
			if (dir[3] == 2) { // ┛
				for (int i = 0; i < 6; i++) {
					if (grid[i][0] == 1) {
						System.out.print(C * getAnswer(i, grid));
					}
				}
			} else { // ┗
				for (int i = 0; i < 6; i++) {
					if (grid[i][0] == 3) {
						System.out.print(C * getAnswer(i, grid));
					}
				}
			}
		}
	}

	private static int getAnswer(int i, int[][] grid) {
		int a = grid[i][1];
		i++;
		if (i >= 6)
			i -= 6;
		a *= grid[i][1];

		i += 2;
		if (i >= 6)
			i -= 6;
		int b = grid[i][1];
		i++;
		if (i >= 6)
			i -= 6;
		b *= grid[i][1];

		return a - b;
	}
}
