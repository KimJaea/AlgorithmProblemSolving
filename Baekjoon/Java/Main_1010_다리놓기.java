// 1010 다리 놓기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			System.out.println(setBridge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
	}

	public static int setBridge(int n, int m) {
		if (n == m)
			return 1;

		int[][] bridge = new int[m + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			bridge[i][0] = 1;
			bridge[i][i] = 1;
		}
		for (int i = 2; i <= m; i++) {
			for (int j = 1; j < i; j++) {
				bridge[i][j] = bridge[i - 1][j - 1] + bridge[i - 1][j];
			}
		}
		return bridge[m][n];
	}

}
