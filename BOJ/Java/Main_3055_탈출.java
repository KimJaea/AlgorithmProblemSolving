import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 3055 탈출

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static boolean fin;

	static int R, C;
	static char[][] grid;
	static boolean[][] visited;

	static Queue<Pos> water = new LinkedList<>();
	static Queue<Pos> dochi = new LinkedList<>();

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			grid[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 'S') {
					visited[i][j] = true;
					dochi.add(new Pos(i, j));
					grid[i][j] = '.';
				} else if (grid[i][j] == '*') {
					water.add(new Pos(i, j));
				}
			}
		}

		int answer = -1;

		int count = 0;
		while (!dochi.isEmpty()) {
			count++;
			spreadWater();
			moveDochi();
			if (fin) {
				answer = count;
				break;
			}
		}

		if (answer == -1)
			System.out.print("KAKTUS");
		else
			System.out.print(answer);
	}

	public static void moveDochi() {
		int size = dochi.size();
		while (size-- > 0) {
			Pos cur = dochi.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue; // 격자 밖
				if (visited[nx][ny])
					continue; // 이미 방문
				if (grid[nx][ny] == 'D') {
					fin = true; // 비버의 굴 발견
					return;
				}
				if (grid[nx][ny] == '.') {
					visited[nx][ny] = true;
					dochi.add(new Pos(nx, ny));
				}
			}
		}
	}

	public static void spreadWater() {
		int size = water.size();

		while (size-- > 0) {
			Pos cur = water.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue; // 격자 밖
				if (grid[nx][ny] == '.') {
					grid[nx][ny] = '*';
					water.add(new Pos(nx, ny));
				}
			}
		}
	}

	// 시간 초과 발생
	public static boolean movable() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == '.')
					return true;
			}
		}
		return false;
	}
}
