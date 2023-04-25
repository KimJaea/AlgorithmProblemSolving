import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1194 달이 차오른다, 가자.

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

	static int N, M;
	static char[][] grid;
	static int[][][] answer;

	static int stx, sty; // start point
	static ArrayList<Pos> end; // end point

	static Queue<Pos> queue = new LinkedList<>();

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new char[N][M];
		answer = new int[N][M][64]; // 2^6 = 64
		end = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				grid[i][j] = str.charAt(j);

				if (grid[i][j] == '.' || grid[i][j] == '#') // 빈 칸 혹은 벽
					continue;

				if (grid[i][j] == '0') { // 영식이
					stx = i;
					sty = j;
					grid[i][j] = '.';
				} else if (grid[i][j] == '1') { // 출구
					end.add(new Pos(i, j, 0));
				} else { // 문 혹은 열쇠
					// do nothing
				}
			}
		}

		answer[stx][sty][0] = 1; // start point
		queue.add(new Pos(stx, sty, 0));
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int count = answer[cur.x][cur.y][cur.k];

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (grid[nx][ny] == '#')
					continue;

				if (grid[nx][ny] == '.') {
					if (answer[nx][ny][cur.k] == 0) {
						answer[nx][ny][cur.k] = count + 1;
						queue.add(new Pos(nx, ny, cur.k));
					}
				} else if (grid[nx][ny] == '1') {
					if (answer[nx][ny][cur.k] == 0) {
						answer[nx][ny][cur.k] = count + 1;
					}
				} else { // 문 혹은 열쇠
					if (grid[nx][ny] <= 'F') { // 문
						if (answer[nx][ny][cur.k] == 0) {
							int door = 1 << (int) (grid[nx][ny] - 'A');
							if ((cur.k & door) > 0) {
								answer[nx][ny][cur.k] = count + 1;
								queue.add(new Pos(nx, ny, cur.k));
							}
						}
					} else { // 열쇠
						int key = 1 << (int) (grid[nx][ny] - 'a');
						key = (key | cur.k);
						if (answer[nx][ny][key] == 0) {
							answer[nx][ny][key] = count + 1;
							queue.add(new Pos(nx, ny, key));
						}

					}
				}
			}
		}

		int A = Integer.MAX_VALUE;
		for (int i = 0; i < end.size(); i++) {
			for (int j = 0; j < 64; j++) {
				if (answer[end.get(i).x][end.get(i).y][j] > 0) {
					A = Math.min(A, answer[end.get(i).x][end.get(i).y][j]);
				}
			}
		}

		if (A == Integer.MAX_VALUE)
			A = 0;

		bw.write(String.valueOf(A - 1));
		bw.close();
	}
}
