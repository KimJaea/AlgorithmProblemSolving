import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// static StringTokenizer st = null;

	static int N;
	static int[][] grid; // true: 1, false: 0

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				if (str.charAt(j) == '1')
					grid[i][j] = 1;
				else
					grid[i][j] = 0;
			}
		}

		quadTree(0, 0, N);
	}

	public static void quadTree(int x, int y, int n) {
		boolean quadable = true;
		int filled = grid[x][y];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[x + i][y + j] != filled) {
					quadable = false;
					j = n;
					i = n;
				}
			}
		}

		if (quadable)
			System.out.print(filled);
		else {
			System.out.print('(');

			quadTree(x, y, n / 2);
			quadTree(x, y + n / 2, n / 2);
			quadTree(x + n / 2, y, n / 2);
			quadTree(x + n / 2, y + n / 2, n / 2);

			System.out.print(')');
		}
	}
}
