import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 17143 낚시왕

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s; // speed
			this.d = d; // direction
			this.z = z; // size
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}

	static int R, C, M;
	static Shark[][] grid;

	// direction: U D R L
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException { // main method
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 낚시 장소는 R * C 크기
		grid = new Shark[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			grid[r][c] = new Shark(r, c, s, d, z);
		}

		int total = 0;

		// 1. 낚시왕이 오른쪽으로 한 칸 이동 (낚시왕은 t열에 위치)
		for (int t = 1; t <= C; t++) {
			// 2. 낚시왕이 위치한 열의 상어 중 낚시왕과 가장 가까운 상어 잡기
			for (int i = 1; i <= R; i++) {
				if (grid[i][t] != null) {
					total += grid[i][t].z;
					grid[i][t] = null;
					i = R;
				}
			}

			// 3. 상어 이동
			moveShark();
		}

		bw.write(String.valueOf(total));
		bw.close();
	}

	public static void moveShark() {
		Queue<Shark> queue = new LinkedList<>();

		Shark[][] tmp = new Shark[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (grid[i][j] != null) {
					queue.add(grid[i][j]);
				}
			}
		}

		while (!queue.isEmpty()) {
			Shark cur = queue.poll();

			int cnt = cur.s;
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;

			while (cnt > 0) {
				switch (d) {
				case 1: // U
					if (cnt >= (r - 1)) {
						cnt -= (r - 1);
						r = 1;
					} else {
						r -= cnt;
						cnt = 0;
					}
					break;
				case 2: // D
					if (cnt >= (R - r)) {
						cnt -= (R - r);
						r = R;
					} else {
						r += cnt;
						cnt = 0;
					}
					break;
				case 3: // R
					if (cnt >= (C - c)) {
						cnt -= (C - c);
						c = C;
					} else {
						c += cnt;
						cnt = 0;
					}
					break;
				case 4: // L
					if (cnt >= (c - 1)) {
						cnt -= (c - 1);
						c = 1;
					} else {
						c -= cnt;
						cnt = 0;
					}
					break;
				}

				if (cnt > 0) { // 상어 방향 바꾸기
					if (d % 2 == 1)
						d++;
					else
						d--;
				}
			}

			if (tmp[r][c] != null) {
				if (tmp[r][c].z < cur.z) {
					tmp[r][c] = new Shark(r, c, cur.s, d, cur.z);
				}
			} else {
				tmp[r][c] = new Shark(r, c, cur.s, d, cur.z);
			}
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (tmp[i][j] != null) {
					grid[i][j] = tmp[i][j];
				} else {
					grid[i][j] = null;
				}
			}
		}

	}
}
