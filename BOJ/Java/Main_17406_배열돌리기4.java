import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, K, ANSWER;
	static int[][] origin;

	static int[][] cal;

	static int[] cal_ser;
	static boolean[] cal_chk;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		origin = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cal = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			cal[i][0] = Integer.parseInt(st.nextToken());
			cal[i][1] = Integer.parseInt(st.nextToken());
			cal[i][2] = Integer.parseInt(st.nextToken());
		}

		ANSWER = 1000000;
		cal_ser = new int[K];
		cal_chk = new boolean[K];
		setCal(0);

		System.out.print(ANSWER);
	}

	public static void setCal(int pos) {
		if (pos == K) {
			rotateArr();
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!cal_chk[i]) {
				cal_chk[i] = true;
				cal_ser[pos] = i;
				setCal(pos + 1);
				cal_chk[i] = false;
			}
		}
	}

	public static void rotateArr() {
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = origin[i][j];
			}
		}

		for (int k = 0; k < K; k++) {
			int cur = cal_ser[k];
			int r = cal[cur][0] - 1;
			int c = cal[cur][1] - 1;
			int s = cal[cur][2];

			for (int i = 1; i <= s; i++) {
				int x = r - i;
				int y = c - i;
				int cnt = 8 * i;
				int cyl = 2 * i;
				int dir = 0; // right, down, left, up

				Queue<Integer> q = new LinkedList<>();
				while (cnt-- > 0) {
					q.add(arr[x][y]);
					if (cyl-- > 0) {
						x += dx[dir];
						y += dy[dir];
					} else {
						cyl = 2 * i - 1;
						dir++;
						x += dx[dir];
						y += dy[dir];
					}
				}

				y++;
				dir = 0;
				cyl = 2 * i - 1;
				while (!q.isEmpty()) {
					arr[x][y] = q.poll();
					if (cyl-- > 0) {
						x += dx[dir];
						y += dy[dir];
					} else {
						cyl = 2 * i - 1;
						dir++;
						if (dir > 3)
							dir -= 4;
						x += dx[dir];
						y += dy[dir];
					}
				}
			}

		}

		int value = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int v = 0;
			for (int j = 0; j < M; j++) {
				v += arr[i][j];
				if (v > value)
					break;
			}
			if (v < value)
				value = v;
		}

		if (value < ANSWER)
			ANSWER = value;
	}

}
