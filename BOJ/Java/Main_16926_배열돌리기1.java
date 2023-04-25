import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, m, r;
	static int[][] origin;
	static int[][] answer;
	static boolean[][] check;

	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		origin = new int[n][m];
		answer = new int[n][m];
		check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int l = Math.min(n, m);
		for (int i = 0; i < l / 2; i++) {
			int x = i, y = i, d = 1;
			// d(direction): down, right, up, left
			int rotateCount = (m + n) * 2 - 4 - (8 * i);

			q = new LinkedList<>();
			for (int j = 0; j < rotateCount; j++) {
				q.add(origin[x][y]);
				switch (d) {
				case 1:
					if (x + 1 < n && !check[x + 1][y]) {
						x++;
					} else {
						y++;
						d++;
					}
					break;
				case 2:
					if (y + 1 < m && !check[x][y + 1]) {
						y++;
					} else {
						x--;
						d++;
					}
					break;
				case 3:
					if (x > 0 && !check[x - 1][y]) {
						x--;
					} else {
						y--;
						d++;
					}
					break;
				case 4:
					if (y > 0 && !check[x][y - 1]) {
						y--;
					} else {
						x++;
						d = 1;
					}
					break;
				}
			}

			int rr = r;
			while (rr-- > 0) {
				switch (d) {
				case 1:
					if (x + 1 < n && !check[x + 1][y]) {
						x++;
					} else {
						y++;
						d++;
					}
					break;
				case 2:
					if (y + 1 < m && !check[x][y + 1]) {
						y++;
					} else {
						x--;
						d++;
					}
					break;
				case 3:
					if (x > 0 && !check[x - 1][y]) {
						x--;
					} else {
						y--;
						d++;
					}
					break;
				case 4:
					if (y > 0 && !check[x][y - 1]) {
						y--;
					} else {
						x++;
						d = 1;
					}
					break;
				}
			}

			while (!q.isEmpty()) {
				answer[x][y] = q.poll();
				check[x][y] = true;

				switch (d) {
				case 1:
					if (x + 1 < n && answer[x + 1][y] == 0) {
						x++;
					} else {
						y++;
						d++;
					}
					break;
				case 2:
					if (y + 1 < m && answer[x][y + 1] == 0) {
						y++;
					} else {
						x--;
						d++;
					}
					break;
				case 3:
					if (x > 0 && answer[x - 1][y] == 0) {
						x--;
					} else {
						y--;
						d++;
					}
					break;
				case 4:
					if (y > 0 && answer[x][y - 1] == 0) {
						y--;
					} else {
						x++;
						d = 1;
					}
					break;
				}
			}

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m - 1; j++) {
				bw.write(String.valueOf(answer[i][j]));
				bw.write(' ');
			}
			bw.write(String.valueOf(answer[i][m - 1]));
			bw.newLine();
		}

		bw.close();
	}

}
