import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 17472 다리 만들기 2

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, A, num;
	static boolean[][] origin; // 원본 지도
	static int[][] grid; // 구역 분류 후 지도

	static int[][] bridges; // 다리 연결 정보
	static boolean[] visited; // 방문 정보

	// R D L U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		origin = new boolean[N][M];
		grid = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (st.nextToken().equals("1"))
					origin[i][j] = true;
			}
		}

		// 구역 분류하기
		num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (origin[i][j] && grid[i][j] == 0) {
					setNumber(num, i, j);
					num++;
				}
			}
		}

		bridges = new int[num][num];
		for (int i = 1; i < num; i++)
			Arrays.fill(bridges[i], 10);

		for (int i = 0; i < N; i++) {
			makeBridge(grid[i][0], i, 1, 0, 0); // go right
		}
		for (int i = 0; i < M; i++) {
			makeBridge(grid[0][i], 1, i, 1, 0); // go down
		}

		A = 100;
		visited = new boolean[num];
		visited[1] = true;
		for (int i = 2; i < num; i++) {
			if (bridges[1][i] < 10) {
				visited[i] = true;
				useBridge(bridges[1][i]);
				visited[i] = false;
			}
		}

		if (A == 100)
			System.out.print(-1);
		else
			System.out.print(A);
	}

	public static boolean visitedAll() {
		for (int i = 1; i < num; i++) {
			if (!visited[i])
				return false;
		}
		return true;
	}

	public static void useBridge(int a) {
		if (visitedAll()) {
			A = Math.min(A, a);
			return;
		}

		for (int i = 1; i < num; i++) {
			if (!visited[i])
				continue;
			for (int j = 1; j < num; j++) {
				if (!visited[j] && bridges[i][j] < 10) {
					visited[j] = true;
					useBridge(a + bridges[i][j]);
					visited[j] = false;
				}
			}
		}
	}

	public static void setNumber(int num, int x, int y) {
		grid[x][y] = num;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (origin[nx][ny] && grid[nx][ny] == 0)
				setNumber(num, nx, ny);
		}
	}

	public static void makeBridge(int n, int x, int y, int d, int c) {
		if (x == N || y == M)
			return;

		int nx = x + dx[d];
		int ny = y + dy[d];
		if (n == 0) { // 만났던 땅의 정보가 없음 -> 새 땅을 만날 때까지 전진
			if (nx < N && ny < M) //
				makeBridge(grid[x][y], nx, ny, d, c);
		} else { // 만났던 땅의 정보가 있음 -> 새 땅을 만났는지 확인 및 다리 길이 확인
			if (grid[x][y] == 0) { // 바다
				if (nx < N && ny < M) //
					makeBridge(n, nx, ny, d, c + 1);
			} else { // 땅
				if (grid[x][y] == n) { // 같은 땅
					if (nx < N && ny < M) //
						makeBridge(n, nx, ny, d, 0);
				} else { // 다른 땅
					int cur = grid[x][y];
					if (c > 1) { // 다리 길이 2 이상
						bridges[cur][n] = Math.min(bridges[cur][n], c);
						bridges[n][cur] = Math.min(bridges[n][cur], c);
					}

					if (nx < N && ny < M) //
						makeBridge(cur, nx, ny, d, 0);
				}
			}
		}
	}
}
