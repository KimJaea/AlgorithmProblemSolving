// 17070 파이프 옮기기 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N;
	static int[][] grid;
	static int[][][] answer;

	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N]; // 0: empty, 1: wall
		answer = new int[N][N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0: 가로, 1: 대각선, 2: 세로
		answer[0][1][0] = 1;

		// 벽일때 생각하기
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				for (int d = 0; d < 3; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx >= N || ny >= N)
						continue;
					if (grid[nx][ny] == 1)
						continue;
					if (d == 1 && (grid[nx][j] == 1 || grid[i][ny] == 1))
						continue;

					if (d == 0) {
						answer[nx][ny][d] += answer[i][j][0] + answer[i][j][1];
					} else if (d == 1) {
						answer[nx][ny][d] += answer[i][j][0] + answer[i][j][1] + answer[i][j][2];
					} else if (d == 2) {
						answer[nx][ny][d] += answer[i][j][1] + answer[i][j][2];
					}
				}
			}
		}

		int A = answer[N - 1][N - 1][0] + answer[N - 1][N - 1][1] + answer[N - 1][N - 1][2];
		System.out.println(A);
	}

}
