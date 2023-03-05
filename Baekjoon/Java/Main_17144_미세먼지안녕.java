import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

// 17144 미세먼지 안녕!

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int R, C, T;
	static int[][] room;

	static boolean[][] check;
	static ArrayList<Pos> windUp, windDown;
	static int cleanUp, cleanDown;

	// R D L U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	// R U L D
	static int[] dx1 = { 0, -1, 0, 1 };
	static int[] dy1 = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];

		boolean found = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());

				// 공기 청정기 위치
				if (room[i][j] == -1) {
					if (found) {
						cleanDown = i;
					} else {
						found = true;
						cleanUp = i;
					}
				}
			}
		}

		// 공기 청정기의 바람 만들기
		windUp = new ArrayList<>();
		int r = cleanUp;
		int c = 1;
		int d = 0;
		while (r != cleanUp || c != 0) {
			windUp.add(new Pos(r, c));
			int nr = r + dx1[d];
			int nc = c + dy1[d];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				d++;
				nr = r + dx1[d];
				nc = c + dy1[d];
			}
			r = nr;
			c = nc;
		}
		windDown = new ArrayList<>();
		r = cleanDown;
		c = 1;
		d = 0;
		while (r != cleanDown || c != 0) {
			windDown.add(new Pos(r, c));
			int nr = r + dx[d];
			int nc = c + dy[d];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				d++;
				nr = r + dx[d];
				nc = c + dy[d];
			}
			r = nr;
			c = nc;
		}

		for (int t = 0; t < T; t++) {
			// 1. 미세먼지 확산
			spreadDust();
			// 2. 공기청정기 작동
			cleanAir();
		}

		int amount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0)
					amount += room[i][j];
			}
		}

		System.out.print(amount);
	}

	public static void spreadDust() {
		int[][] tmp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 확산되지 않는 먼지
				if (room[i][j] < 5) {
					tmp[i][j] += room[i][j];
					continue;
				}

				int total = room[i][j];
				int dust = total / 5;

				// (r, c)의 미세먼지는 상하좌우로 확산
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) // 격자 밖
						continue;
					if (room[nx][ny] == -1) // 공기 청정기
						continue;
					tmp[nx][ny] += dust;
					total -= dust;
				}
				tmp[i][j] += total;
			}
		}

		// 확산된 모습 적용
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				room[i][j] = tmp[i][j];
			}
		}
		room[cleanUp][0] = -1;
		room[cleanDown][0] = -1;
	}

	public static void cleanAir() {
		for (int i = windUp.size() - 1; i > 0; i--) {
			room[windUp.get(i).x][windUp.get(i).y] = room[windUp.get(i - 1).x][windUp.get(i - 1).y];
		}
		room[windUp.get(0).x][windUp.get(0).y] = 0;
		
		for (int i = windDown.size() - 1; i > 0; i--) {
			room[windDown.get(i).x][windDown.get(i).y] = room[windDown.get(i - 1).x][windDown.get(i - 1).y];
		}
		room[windDown.get(0).x][windDown.get(0).y] = 0;
	}
}
