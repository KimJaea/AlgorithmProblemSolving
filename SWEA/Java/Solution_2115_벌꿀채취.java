import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 2115 벌꿀채취

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, C, A, a1, a2;
	static int[][] beehive; // 10 ~ 30
	static int[][] honey;
	static boolean[] isUsed;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 3 ~ 10
			M = Integer.parseInt(st.nextToken()); // 1 ~ 9
			C = Integer.parseInt(st.nextToken()); // 1 ~ 5

			beehive = new int[N][N]; // N * N 벌통
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					beehive[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			A = 0;
			honey = new int[2][M]; // 2명이 고른 M개의 벌통 속 꿀
			selectBeehive(0, 0, 0); // M개의 벌통 고르기

			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(A));
			bw.newLine();
		}

		bw.close();
	}

	public static void selectBeehive(int cnt, int x, int y) {
		if (cnt == 2) { // 두 명이 벌통 고른 경우
			calculateMoney(); // 최대 이익 구하기
			return;
		}

		// x, y에서부터 선택 가능
		for (int j = y; j < N; j++) {
			if (N - j >= M) {
				for (int c = 0; c < M; c++) {
					honey[cnt][c] = beehive[x][j + c];
				}
				selectBeehive(cnt + 1, x, j + M);
			}
		}
		for (int i = x + 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (N - j >= M) {
					for (int c = 0; c < M; c++) {
						honey[cnt][c] = beehive[i][j + c];
					}
					selectBeehive(cnt + 1, i, j + M);
				}
			}
		}

	}

	public static void calculateMoney() {
//		for (int i = 0; i < 2; i++) {
//			System.out.print("\n" + (i + 1) + "번 일꾼이 고른 꿀? ");
//			for (int j = 0; j < M; j++) {
//				System.out.print(honey[i][j] + ", ");
//			}
//		}
//		System.out.println();

		a1 = 0;
		a2 = 0;
		isUsed = new boolean[M];
		selectHoney(0);

		A = Math.max(A, a1 + a2);
	}

	public static void selectHoney(int pos) {
		if (pos == M) {
			getMaxPrice(0);
			getMaxPrice(1);
			return;
		}

		isUsed[pos] = true;
		selectHoney(pos + 1);
		isUsed[pos] = false;
		selectHoney(pos + 1);
	}

	public static void getMaxPrice(int cnt) {
		int amount = 0;
		int price = 0;
		for (int j = 0; j < M; j++) {
			if (isUsed[j]) {
				amount += honey[cnt][j];
				price += honey[cnt][j] * honey[cnt][j];

				if (amount > C)
					return;
			}
		}

		if (cnt == 0) {
			a1 = Math.max(a1, price);
		} else {
			a2 = Math.max(a2, price);
		}
	}
}
