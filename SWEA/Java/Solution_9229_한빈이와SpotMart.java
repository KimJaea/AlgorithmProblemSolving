import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, m, maxWeight;
	static int[] snacks;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			snacks = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			maxWeight = -1;
			for (int i = 0; i < n - 1; i++) {
				if (snacks[i] > m)
					continue;
				for (int j = i + 1; j < n; j++) {
					if (snacks[j] > m)
						continue;
					int cur = snacks[i] + snacks[j];
					if (cur <= m && cur > maxWeight) {
						maxWeight = cur;
					}
				}
				if (maxWeight == m)
					i = n;
			}

			System.out.printf("#%d %d\n", t + 1, maxWeight);
		}

	}

}
