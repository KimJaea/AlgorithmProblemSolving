import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static boolean[][] grid = new boolean[101][101];
	static int n, answer;

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());

		for (int t = 0; t < n; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					grid[x + i][y + j] = true;
				}
			}
		}

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (grid[i][j])
					answer++;
			}
		}

		System.out.println(answer);
	}
}
