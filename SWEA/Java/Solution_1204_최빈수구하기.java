import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1204 최빈수 구하기

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int bestScore, bestCount;
	static int[] scores;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int tmp = Integer.parseInt(br.readLine());

			bestScore = 0;
			bestCount = 0;
			scores = new int[101];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 1000; i++) {
				int cur = Integer.parseInt(st.nextToken());
				scores[cur]++;
				if (scores[cur] > bestCount) {
					bestScore = cur;
					bestCount = scores[cur];
				} else if (scores[cur] == bestCount) {
					if (cur > bestScore) {
						bestScore = cur;
					}
				}
			}

			bw.write('#' + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(bestScore));
			bw.newLine();
		}

		bw.close();
	}
}
