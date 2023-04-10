import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 17136 색종이 붙이기

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int A;
	static int[] papers = new int[6];
	static int[][] grid = new int[10][10];

	public static void main(String[] args) throws NumberFormatException, IOException {

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		A = Integer.MAX_VALUE;
		fillPaper(0, 0, 0);

		if (A == Integer.MAX_VALUE)
			System.out.print(-1);
		else
			System.out.print(A);
	}

	private static void fillPaper(int x, int y, int c) {
		while (grid[x][y] == 0) {
			y++;
			if (y == 10) {
				x++;
				y = 0;
			}
			if (x == 10) {
				A = Math.min(A, c);
				return;
			}
		}

		for (int n = 5; n > 0; n--) {
			if (papers[n] == 5)
				continue;
			if (ablePaper(x, y, n)) {
				papers[n]++;
				setPaper(x, y, n, 0);
				fillPaper(x, y, c + 1);
				setPaper(x, y, n, 1);
				papers[n]--;
			}
		}

	}

	private static boolean ablePaper(int x, int y, int n) {
		if (x + n - 1 >= 10 || y + n - 1 >= 10)
			return false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[x + i][y + j] == 0)
					return false;
			}
		}

		return true;
	}

	private static void setPaper(int x, int y, int n, int v) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[x + i][y + j] = v;
			}
		}
	}
}
