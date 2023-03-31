// 1600 말이 되고픈 원숭이

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int x, y, k;

	public Pos(int x, int y, int k) {
		this.x = x;
		this.y = y;
		this.k = k;
	}
}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int K, W, H;
	static boolean[][] map;
	static int[][][] answer;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static int[] rx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] ry = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		answer = new int[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				Arrays.fill(answer[i][j], 100000);

				if (st.nextToken().equals("1"))
					map[i][j] = true;
				else
					map[i][j] = false;
			}
		}

		answer[0][0][K] = 0;
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, K)); // x, y, k

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny])
					continue;

				if (answer[nx][ny][cur.k] > answer[cur.x][cur.y][cur.k] + 1) {
					answer[nx][ny][cur.k] = answer[cur.x][cur.y][cur.k] + 1;
					queue.add(new Pos(nx, ny, cur.k));
				}
			}

			if (cur.k == 0)
				continue;

			for (int r = 0; r < 8; r++) {
				int nx = cur.x + rx[r];
				int ny = cur.y + ry[r];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny])
					continue;

				if (answer[nx][ny][cur.k - 1] > answer[cur.x][cur.y][cur.k] + 1) {
					answer[nx][ny][cur.k - 1] = answer[cur.x][cur.y][cur.k] + 1;
					queue.add(new Pos(nx, ny, cur.k - 1));
				}
			}

		}

		int A = Integer.MAX_VALUE;
		for (int i = 0; i <= K; i++)
			A = Math.min(A, answer[H - 1][W - 1][i]);

		if (A == 100000)
			System.out.println(-1);
		else
			System.out.print(A);
	}
}
