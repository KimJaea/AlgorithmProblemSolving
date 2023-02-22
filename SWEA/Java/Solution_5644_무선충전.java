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

	static int M, A, answer;
	static int[][] moving;
	static int[][] charger;

	// 제자리, 상, 우, 하, 좌
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			moving = new int[2][M];
			charger = new int[A][5]; // x, y, c, p

			// 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moving[0][i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moving[1][i] = Integer.parseInt(st.nextToken());
			}

			// 충전기 정보
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				// 좌표, 범위, 처리량
				charger[i][0] = Integer.parseInt(st.nextToken());
				charger[i][1] = Integer.parseInt(st.nextToken());
				charger[i][2] = Integer.parseInt(st.nextToken());
				charger[i][3] = Integer.parseInt(st.nextToken());
			}

			answer = 0;
			int x1 = 1, y1 = 1, x2 = 10, y2 = 10;
			charge(x1, y1, x2, y2);
			for (int i = 0; i < M; i++) {
				x1 += dx[moving[0][i]];
				y1 += dy[moving[0][i]];
				x2 += dx[moving[1][i]];
				y2 += dy[moving[1][i]];
				charge(x1, y1, x2, y2);
			}

			System.out.printf("#%d %d\n", t + 1, answer);
		}
	}

	// 충전 정보 연산
	public static void charge(int x1, int y1, int x2, int y2) {
		int dis = 0;

		boolean[][] reach = new boolean[A][2]; // i번째 충전기와 충전 가능 여부를 나타낸다.

		for (int j = 0; j < A; j++) {
			dis = Math.abs(charger[j][0] - x1) + Math.abs(charger[j][1] - y1);
			if (dis <= charger[j][2])
				reach[j][0] = true;
			dis = Math.abs(charger[j][0] - x2) + Math.abs(charger[j][1] - y2);
			if (dis <= charger[j][2])
				reach[j][1] = true;
		}

		int best = 0;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int cur = 0;
				if (i == j) {
					if (reach[i][0] || reach[j][1])
						cur = charger[i][3]; // charger[j][3];
				} else {
					if (reach[i][0])
						cur += charger[i][3];
					if (reach[j][1])
						cur += charger[j][3];
				}

				if (cur > best) {
					best = cur;
				}
			}
		}

		answer += best;
	}

	//
}
