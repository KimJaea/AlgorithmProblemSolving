import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 16919 봄버맨 2

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int R, C, N;
	static int[][] grid;
	static boolean[][] boom;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		grid = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				if (str.charAt(j) == 'O')
					grid[i][j] = 2;
			}
		}

		// 1초 후의 모양은 동일
		if (N == 1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (grid[i][j] > 0)
						System.out.print('O');
					else
						System.out.print('.');
				}
				System.out.println();
			}
			return;
		}

		// 짝수 초 후의 모양은 모두 폭탄
		if (N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
			return;
		}

		N--;
		N %= 4;
		if (N == 0)
			N = 4;
		
		boom = new boolean[R][C];
		boolean fill = true; // true: fill, false: boom
		while (N-- > 0) {
			printGrid();
			if (fill) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (grid[i][j] == 0)
							grid[i][j] = 3;
						else
							grid[i][j]--;
					}
				}
			} else {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (grid[i][j] > 0)
							grid[i][j]--;
						if (grid[i][j] == 0) {
							bomb(i, j);
						}
					}
				}
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (boom[i][j]) {
							grid[i][j] = 0;
							boom[i][j] = false;
						}
					}
				}
			}
			fill = !fill;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] > 0)
					System.out.print('O');
				else
					System.out.print('.');
			}
			System.out.println();
		}
	}

	private static void printGrid() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(grid[i][j]);
//				if (grid[i][j] > 0)
//					System.out.print('O');
//				else
//					System.out.print('.');
			}
			System.out.println();
		}
		System.out.println("=========================");

	}

	private static void bomb(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			boom[nx][ny] = true;
		}
	}

}
