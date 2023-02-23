import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1987 알파벳

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int R, C, A;
	static char[][] grid;
	static boolean[][] check;
	static boolean[] alphabet;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		check = new boolean[R][C];
		alphabet = new boolean[26];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				grid[i][j] = str.charAt(j);
			}
		}

		A = 0;
		alphabet[grid[0][0] - 'A'] = true;
		check[0][0] = true;
		moving(0, 0, 1);
		
		bw.write(String.valueOf(A));
		bw.close();
	}

	public static void moving(int x, int y, int c) {
		A = Math.max(A, c);
		
		for (int i = 0; i < 4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			if (check[nx][ny] || alphabet[grid[nx][ny] - 'A'])
				continue;
			alphabet[grid[nx][ny] - 'A'] = true;
			check[nx][ny] = true;
			moving(nx, ny, c + 1);
			check[nx][ny] = false;
			alphabet[grid[nx][ny] - 'A'] = false;
		}
	}
	
}
