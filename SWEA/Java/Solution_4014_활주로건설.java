import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 4014 활주로 건설

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, X, A;
	static int[][] grid;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			A = 0;
			for (int i = 0; i < N; i++) {
				if (checkRow(i)) { // i번째 행 확인
					A++;
				}
				if (checkCol(i)) { // i번째 열 확인
					A++;
				}
			}

			bw.write("#" + String.valueOf(t));
			bw.write(" " + String.valueOf(A) + "\n");
		}
		bw.close();
	}

	public static boolean checkRow(int c) { // 행 확인
		int ser = 1;
		int prev = grid[c][0];

		for (int i = 1; i < N; i++) {
			int cur = grid[c][i];

			if (cur == prev) {
				ser++;
			} else {
				if (Math.abs(prev - cur) > 1) // 단차가 2 이상
					return false;

				if (prev < cur) { // 오르막
					if (ser < X) // 활주로 공간이 없음
						return false;
					prev = cur;
					ser = 1;
				} else { // 내리막
					if (ser < 0)
						return false;
					prev = cur;
					ser = 1 - X;
				}
			}
		}

		if (ser < 0)
			return false;
		return true;
	}

	public static boolean checkCol(int c) { // 열 확인
		int ser = 1;
		int prev = grid[0][c];

		for (int i = 1; i < N; i++) {
			int cur = grid[i][c];
			if (cur == prev) {
				ser++;
			} else {
				if (Math.abs(prev - cur) > 1) // 단차가 2 이상
					return false;
				if (prev < cur) { // 오르막
					if (ser < X) // 활주로 공간이 없음
						return false;
					prev = cur;
					ser = 1;
				} else { // 내리막
					if (ser < 0)
						return false;
					prev = cur;
					ser = 1 - X;
				}
			}
		}
		
		if (ser < 0)
			return false;		
		return true;
	}
}
