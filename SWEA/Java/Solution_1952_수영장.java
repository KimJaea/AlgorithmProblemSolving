import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int answer;
	static int[] price = new int[4];
	static int[] swim = new int[12];

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				swim[i] = Integer.parseInt(st.nextToken());
			}

			answer = price[3];
			calTotal(0, 0);
			System.out.printf("#%d %d\n", t + 1, answer);
		}

		bw.close();
	}

	public static void calTotal(int cur, int total) {
		if (total > answer)
			return;

		if (cur >= 12) {
			if (total < answer)
				answer = total;
		} else {
			if (swim[cur] == 0) {
				calTotal(cur + 1, total);
			}
			calTotal(cur + 1, total + swim[cur] * price[0]);
			calTotal(cur + 1, total + price[1]);
			calTotal(cur + 3, total + price[2]);
			
		}
	}
}
