import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1954 달팽이 숫자

public class Solution {

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			int[][] grid = new int[N][N];
			int[] dx = { 0, 1, 0, -1 };
			int[] dy = { 1, 0, -1, 0 };

			int num = 1, x = 0, y = 0, d = 0;
			while (num <= N * N) {
				grid[x][y] = num++;
				int nx = x + dx[d];
				int ny = y + dy[d];

				boolean movable = true;
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					movable = false;
				} else if (grid[nx][ny] > 0) {
					movable = false;
				}

				if (!movable) {
					d++;
					if (d >= 4)
						d -= 4;
					nx = x + dx[d];
					ny = y + dy[d];
				}

				x = nx;
				y = ny;
			}


			bw.write('#' + String.valueOf(t + 1));
			bw.newLine();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.write(String.valueOf(grid[i][j]) + ' ');
				}
				bw.newLine();
			}
		}

		bw.close();
	}

}
