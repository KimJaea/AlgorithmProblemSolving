import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 14510 나무 높이

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, height, answer;
	static int[] tree;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		dp = new int[12000];
		// dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i < 12000; i++) {
			int bef1 = dp[i - 1] % 2 == 0 ? dp[i - 1] + 1 : dp[i - 1] + 2;
			int bef2 = dp[i - 2] % 2 == 1 ? dp[i - 2] + 1 : dp[i - 2] + 2;
			dp[i] = Math.min(bef1, bef2);
		}

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N];
			height = 0;
			answer = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				height = Math.max(height, tree[i]);
			}

			int day = 0, len1 = 0, len2 = 0;

			for (int i = 0; i < N; i++) {
				if (tree[i] == height)
					continue;
				int cur = height - tree[i];

				len2 += cur / 2;
				if (cur % 2 == 1)
					len1++;
			}

			if (len1 > len2) {
				day += len1 * 2;
				day--;
			} else if (len1 == len2) {
				day += len1 * 2;
			} else { // len1 < len2
				day += len1 * 2;
				len2 -= len1;

				day += dp[len2 * 2];
			}

			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(day));
			bw.newLine();
		}

		bw.close();

	}
}
