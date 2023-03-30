// 14502 연구소

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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

	static int N, M, A;
	static int[][] origin;
	static int[][] copied;

	static ArrayList<Pos> spaces = new ArrayList<>();
	static ArrayList<Pos> viruses = new ArrayList<>();

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		origin = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());

				if (origin[i][j] == 0)
					spaces.add(new Pos(i, j));
				else if (origin[i][j] == 2)
					viruses.add(new Pos(i, j));
			}
		}

		makeWall(0, 0);
		System.out.print(A);
	}

	private static void printGrid(int[][] grid) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void makeWall(int n, int c) {
		// 1. 벽 세울 곳 3곳 뽑기

		if (c == 3) {
			spreadVirus();
			return;
		}

		if (n >= spaces.size())
			return;
		
		Pos cur = spaces.get(n);
		origin[cur.x][cur.y] = 1;
		makeWall(n + 1, c + 1);
		origin[cur.x][cur.y] = 0;
		makeWall(n + 1, c);
	}

	public static void spreadVirus() {
		// 2. 벽 세우고 바이러스 전파되기

		copied = new int[N][M];
		for (int i = 0; i < N; i++) {
			copied[i] = origin[i].clone();
		}
        
		Queue<Pos> queue = new LinkedList<>();
		for (int i = 0; i < viruses.size(); i++) {
			queue.add(viruses.get(i));
		}

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (copied[nx][ny] == 0) {
					copied[nx][ny] = 2;
					queue.add(new Pos(nx, ny));
				}
			}
		}

		A = Math.max(A, countSpace());
	}

	public static int countSpace() {
		// 3. 남은 빈 공간 크기 구하기

		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copied[i][j] == 0)
					total++;
			}
		}
		return total;
	}
}
