import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, minResult, maxResult;
	static int[] operator; // +, -, *, /
	static int[] operand;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			operator = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}

			operand = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				operand[i] = Integer.parseInt(st.nextToken());
			}

			minResult = Integer.MAX_VALUE;
			maxResult = Integer.MIN_VALUE;
			calResult(1, operand[0]);
			System.out.printf("#%d %d\n", t + 1, maxResult - minResult);
		}
	}

	public static void calResult(int pos, int cur) {
		if (pos == n) {
			if (cur < minResult)
				minResult = cur;
			if (cur > maxResult)
				maxResult = cur;
		} else {
			for (int i = 0; i < 4; i++) {
				if (operator[i] > 0) {
					operator[i]--;

					if (i == 0)
						calResult(pos + 1, cur + operand[pos]);
					else if (i == 1)
						calResult(pos + 1, cur - operand[pos]);
					else if (i == 2)
						calResult(pos + 1, cur * operand[pos]);
					else
						calResult(pos + 1, cur / operand[pos]);

					operator[i]++;
				}
			}
		}

	}
}
