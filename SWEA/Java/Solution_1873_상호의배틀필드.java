import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1873 상호의 배틀필드

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int x, y;
	static int H, W, N;
	static char[][] grid;
	static char[] tank;

	// U D L R: 상 하 좌 우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[] dc = { '^', 'v', '<', '>' };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			grid = new char[H][W];
			for (int i = 0; i < H; i++) {
				grid[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					for (int c = 0; c < 4; c++) {
						if (grid[i][j] == dc[c]) {
							x = i;
							y = j;
						}
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			tank = new char[N];
			tank = br.readLine().toCharArray();

			// 탱크 연산
			for (int n = 0; n < N; n++) {
				if (tank[n] == 'S') {
					int d = -1;
					for (int c = 0; c < 4; c++) {
						if (grid[x][y] == dc[c]) {
							d = c;
							c = 4;
						}
					}

					int nx = x + dx[d];
					int ny = y + dy[d];
					while (nx >= 0 && nx < H && ny >= 0 && ny < W) {
						if (grid[nx][ny] == '*') {
							grid[nx][ny] = '.';
							break;
						} else if (grid[nx][ny] == '#') {
							break;
						}
						nx += dx[d];
						ny += dy[d];
					}

				} else {
					int d = 0;
					switch (tank[n]) {
					case 'R':
						d++;
					case 'L':
						d++;
					case 'D':
						d++;
					case 'U':
						break;
					}
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && grid[nx][ny] == '.') {
						grid[x][y] = '.';
						x = nx;
						y = ny;
						grid[x][y] = dc[d];
					} else {
						grid[x][y] = dc[d];
					}

				}
			}

			bw.write("#" + String.valueOf(t + 1) + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					bw.write(grid[i][j]);
				}
				bw.newLine();
			}
		}
		bw.close();

	}

}