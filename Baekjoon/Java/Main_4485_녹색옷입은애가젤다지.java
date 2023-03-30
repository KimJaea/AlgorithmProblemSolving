// 4485 녹색 옷 입은 애가 젤다지?

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
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N;
	static int[][] cave;
	static int[][] answer;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());

		int T = 1;
		while (N != 0) {
			cave = new int[N][N];
			answer = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					answer[i][j] = Integer.MAX_VALUE;
				}
			}

			answer[0][0] = cave[0][0];
			Queue<Pos> queue = new LinkedList<>();
			queue.add(new Pos(0, 0));
			while (!queue.isEmpty()) {
				Pos cur = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (answer[nx][ny] > answer[cur.x][cur.y] + cave[nx][ny]) {
						answer[nx][ny] = answer[cur.x][cur.y] + cave[nx][ny];
						queue.add(new Pos(nx, ny));
					}
				}
			}

			bw.write("Problem " + String.valueOf(T) + ": ");
			bw.write(String.valueOf(answer[N - 1][N - 1]) + "\n");
			N = Integer.parseInt(br.readLine());
			T++;
		}
		bw.close();
	}
}
