import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 17281 야구

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, answer;
	static int[][] result;

	static int[] player;
	static boolean[] checked;
	static int[] ground;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		result = new int[10][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				result[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		// 선수 순서 결정
		player = new int[9]; // 선수의 순서
		checked = new boolean[10];
		player[3] = 1;
		checked[1] = true;

		answer = 0;
		setPlayer(0);

		bw.write(String.valueOf(answer));
		bw.close();
	}

	public static void setPlayer(int pos) {
		if (pos == 9) {
			playGame();
			return;
		}

		// 1번 선수가 4번 타자
		if (pos == 3) {
			setPlayer(pos + 1);
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (checked[i])
				continue;

			checked[i] = true;
			player[pos] = i;
			setPlayer(pos + 1);
			// player[pos] = -1;
			checked[i] = false;
		}
	}

	public static void playGame() {
		int cur_num = 0; // 타자 순번

		ground = new int[4];

		int n = 0; // 이닝 수
		int out_cnt = 0, point = 0; // 아웃 수와 점수
		while (n < N) {
			int cur_player = player[cur_num]; // 타자 선수 번호
			switch (result[cur_player][n]) {
			case 0: // 아웃
				out_cnt++;
				break;
			case 1: // 안타
				point += moveRu(1, cur_player);
				break;
			case 2: // 2루타
				point += moveRu(2, cur_player);
				break;
			case 3: // 3루타
				point += moveRu(3, cur_player);
				break;
			case 4: // 홈런
				point += moveRu(4, cur_player);
				break;
			}

			cur_num++;
			if (cur_num == 9)
				cur_num = 0;
			if (out_cnt == 3) {
				n++;
				out_cnt = 0;
				ground = new int[4];
			}
		}

		answer = Math.max(answer, point);
	}

	public static int moveRu(int cnt, int num) {
		int score = 0;
		for (int i = 3; i >= 0; i--) {
			if (ground[i] == 0)
				continue;
			int pos = i + cnt;
			if (pos > 3)
				score++;
			else
				ground[pos] = ground[i];
			ground[i] = 0;
		}

		if (cnt == 4)
			score++;
		else
			ground[cnt] = num;

		return score;
	}
}
