// 12865 평범한 배낭

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] W = new int[N + 1];
		int[] V = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			W[n] = Integer.parseInt(st.nextToken());
			V[n] = Integer.parseInt(st.nextToken());
		}

		int[][] A = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) { // 물건
			for (int j = 1; j <= K; j++) { // 무게
				if (j >= W[i]) {
					A[i][j] = V[i] + A[i - 1][j - W[i]];
				}

				A[i][j] = Math.max(A[i][j], A[i][j - 1]);
				A[i][j] = Math.max(A[i][j], A[i - 1][j]);

			}
		}

//		for (int i = 1; i <= N; i++) { // 물건
//			for (int j = 1; j <= K; j++) { // 무게
//				System.out.print(A[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.print(A[N][K]);
	}

}
