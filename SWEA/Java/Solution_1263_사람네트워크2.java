import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1263 사람 네트워크2

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, A;
	static int[][] relation;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			relation = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (st.nextToken().equals("1"))
						relation[i][j] = 1;
					else
						relation[i][j] = 1000000;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						relation[i][k] = Integer.min(relation[i][k], relation[i][j] + relation[j][k]);
					}
				}
			}

			A = 1000000;
			for (int i = 0; i < N; i++) {
				int total = 0;
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					if (relation[i][j] < 1000000)
						total += relation[i][j];
				}
				if (total < A)
					A = total;
			}

			bw.write("#" + String.valueOf(t) + " ");
			bw.write(String.valueOf(A) + "\n");
		}
		bw.close();
	}

	public static void dfs() {

	}
}
