import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, A;
	static int[][] S;
	static boolean[] dish; // true: a, false: b

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			S = new int[N][N];
			dish = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			A = Integer.MAX_VALUE;
			dish[0]=true;
			devideDish(1, 1, 0);
//			dish[0]=false;
//			devideDish(1, 0, 1);
			
			System.out.printf("#%d %d\n", t + 1, A);
		}
	}

	public static void devideDish(int p, int a, int b) {
		if (p == N) {
			if (a == b) {
				calculateS();
			}
			return;
		}

		dish[p] = true;
		devideDish(p + 1, a + 1, b);
		dish[p] = false;
		devideDish(p + 1, a, b + 1);
	}

	public static void calculateS() {
		int ai = 0, bi = 0;
		int[] a = new int[N / 2];
		int[] b = new int[N / 2];
		for (int i = 0; i < N; i++) {
			if (dish[i]) {
				a[ai++] = i;
			} else {
				b[bi++] = i;
			}
		}

		int total1 = 0, total2 = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				if (i == j)
					continue;
				total1 += S[a[i]][a[j]];
				total2 += S[b[i]][b[j]];
			}
		}
		int total = Math.abs(total1 - total2);
		if (total < A)
			A = total;
	}

}