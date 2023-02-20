import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Pipe {
	int x, y, t;

	Pipe(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, R, C, L;
	static int[][] tunnel;
	static boolean[][] answer;
	static Queue<Pipe> escape;

	// 상 우 하 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			tunnel = new int[N][M];
			answer = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tunnel[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int total = 0;

			escape = new LinkedList<>();
			escape.add(new Pipe(R, C, 1));

			while (!escape.isEmpty()) {
				Pipe cur = escape.poll();
				if(answer[cur.x][cur.y]) continue;
				answer[cur.x][cur.y] = true;
				total++;
				if (cur.t == L)
					continue;
				switch (tunnel[cur.x][cur.y]) {
				case 1: // 사방
					for (int i = 0; i < 4; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 2: // 상하
					for (int i = 0; i < 4; i += 2) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 3: // 우좌
					for (int i = 1; i < 4; i += 2) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 4: // 상우
					for (int i = 0; i < 2; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 5: // 우하
					for (int i = 1; i < 3; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 6: // 하좌
					for (int i = 2; i < 4; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;
				case 7: // 상좌
					for (int i = 0; i < 4; i += 3) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							continue;
						if (tunnel[nx][ny] == 0 || answer[nx][ny])
							continue;
						if (canGoPipe(cur.x, cur.y, nx, ny))
							escape.add(new Pipe(nx, ny, cur.t + 1));
					}
					break;

				}
			}
			System.out.printf("#%d %d\n", t + 1, total);
		}
	}

	public static boolean canGoPipe(int x1, int y1, int x2, int y2) {
		int target = tunnel[x2][y2];
		if (x1 > x2) { 
			if (target == 1 || target == 2 || target == 5 || target == 6)
				return true;
			else
				return false;
		} else if (x2 > x1) { 
			if (target == 1 || target == 2 || target == 4 || target == 7)
				return true;
			else
				return false;
		} else { 
			if (y1 > y2) { 
				if (target == 1 || target == 3 || target == 4 || target == 5)
					return true;
				else
					return false;
			} else { 
				if (target == 1 || target == 3 || target == 6 || target == 7)
					return true;
				else
					return false;
			}
		}

	}

}
