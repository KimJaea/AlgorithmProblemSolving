import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 16236 아기 상어

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
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, time;
	static int sx, sy, ss, tx, ty, tt;

	static int[][] grid;

	// L D R U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] == 9) {
					sx = i;
					sy = j;
					ss = 2;
				}
			}
		}

		int ate = 0;
		tx = N;
		ty = N;
		
		while (eatable()) {
			grid[sx][sy] = 0;
			time += tt;
			grid[tx][ty] = 9;
			sx = tx;
			sy = ty;
			ate++;
			if (ate == ss) {
				ate = 0;
				ss++;
			}
		}

		System.out.print(time);
	}

	public static boolean eatable() {
		boolean[][] isVisited = new boolean[N][N];

		Queue<Pos> queue = new LinkedList();
		queue.add(new Pos(sx, sy));
		isVisited[sx][sy] = true;

		int dis = 1;
		boolean found = false;
		while (!queue.isEmpty()) {

			int size = queue.size();
			while (size-- > 0) {
				
				Pos cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (isVisited[nx][ny])
						continue;
					if (grid[nx][ny] > ss) {
						isVisited[nx][ny] = true;
						continue;
					}

					if (grid[nx][ny] == ss || grid[nx][ny] == 0) {
						queue.add(new Pos(nx, ny));
					} else {
						if (found) {
							if (nx < tx) {
								tx = nx;
								ty = ny;
							} else {
								if (ny < ty) {
									tx = nx;
									ty = ny;
								}
							}
							if (tx == nx) {
								ty = Math.min(ty, ny);
							} else {
								if (ty == ny) {
									tx = Math.min(tx, nx);
								} else {
									// 
								}
							}
						} else {
							found = true;
							tx = nx;
							ty = ny;
						}
					}

					isVisited[nx][ny] = true;
				}
			}

			if (found)
				break;

			dis++;
		} // 전체 반복 끝

		tt = dis;

		return found;
	} // 함수 끝
}
