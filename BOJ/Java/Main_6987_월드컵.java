import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static boolean able;
	static int[][] result;
	static int[][] game;

	public static void main(String[] args) throws IOException {
		for (int t = 0; t < 4; t++) {
			able = true;

			result = new int[6][4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				int w = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());

				result[i][0] = w;
				result[i][1] = d;
				result[i][2] = l;
				if (w + d + l != 5)
					able = false;
			}

			if (able) {
				able = false;
				game = new int[6][4];
				setGame(0, 0, 1);
			}
			
			// 출력
			if (able)
				bw.write("1 ");
			else
				bw.write("0 ");
		}
		bw.close();
	}

	public static void setGame(int cnt, int c1, int c2) {
		if (cnt == 15) {
			able = true;
			return;
		}

		// c1 이기는 경우
		if (game[c1][0] < result[c1][0] && game[c2][2] < result[c2][2]) {
			game[c1][0]++;
			game[c2][2]++;
			if (c2 == 5)
				setGame(cnt + 1, c1 + 1, c1 + 2);
			else
				setGame(cnt + 1, c1, c2 + 1);
			game[c1][0]--;
			game[c2][2]--;
		}

		// 비기는 경우
		if (game[c1][1] < result[c1][1] && game[c2][1] < result[c2][1]) {
			game[c1][1]++;
			game[c2][1]++;
			if (c2 == 5)
				setGame(cnt + 1, c1 + 1, c1 + 2);
			else
				setGame(cnt + 1, c1, c2 + 1);
			game[c1][1]--;
			game[c2][1]--;
		}

		// c1 지는 경우
		if (game[c1][2] < result[c1][2] && game[c2][0] < result[c2][0]) {
			game[c1][2]++;
			game[c2][0]++;
			if (c2 == 5)
				setGame(cnt + 1, c1 + 1, c1 + 2);
			else
				setGame(cnt + 1, c1, c2 + 1);
			game[c1][2]--;
			game[c2][0]--;
		}
	}

}
