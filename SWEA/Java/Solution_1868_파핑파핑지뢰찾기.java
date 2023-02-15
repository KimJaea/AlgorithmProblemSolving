import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, checked, total;
	static String tmp;
	static boolean[][] grid; // 지뢰 유무 판단, false: 지뢰 없음
	static boolean[][] check; // 방문 여부 판단
	static int[][] answer; // 지뢰찾기 결과 숫자

	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			checked = 0;
			total = 0;

			grid = new boolean[n][n];
			check = new boolean[n][n];
			answer = new int[n][n];

			for (int i = 0; i < n; i++) {
				tmp = br.readLine();
				for (int j = 0; j < n; j++) {
					if (tmp.charAt(j) == '.') {
						grid[i][j] = false;
					} else {
						grid[i][j] = true;
						check[i][j] = true;
						checked++;
					}
					answer[i][j] = -1;
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j])
						answer[i][j] = -1; // 지뢰
					else {
						int cur = 0;
						for (int d = 0; d < 8; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (nx < 0 || nx >= n || ny < 0 || ny >= n)
								continue;
							if (grid[nx][ny])
								cur++;
						}
						answer[i][j] = cur;
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (answer[i][j] == 0 && !check[i][j]) {
						check[i][j] = true;
						checked++;
						clickGrid(i, j);
						total++;
					}
				}
			}

			total += (n * n) - checked;

			System.out.printf("#%d %d\n", t + 1, total);
		}

	}

	public static void clickGrid(int i, int j) {
		for (int d = 0; d < 8; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			if (check[nx][ny])
				continue;
			if (answer[nx][ny] == 0) {
				check[nx][ny] = true;
				checked++;
				clickGrid(nx, ny);
			} else {
				check[nx][ny] = true;
				checked++;
			}
		}
	}

}
