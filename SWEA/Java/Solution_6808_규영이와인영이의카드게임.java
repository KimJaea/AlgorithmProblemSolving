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

	static int[] gyu;
	static int[] iny;
	static boolean[] card; // true: 규영, false: 인영

	static int winning, losing;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			gyu = new int[9];
			iny = new int[9];
			card = new boolean[19];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				card[gyu[i]] = true;
			}

			winning = 0;
			losing = 0;
			setIny(0, 0, 0);

			System.out.printf("#%d %d %d\n", t + 1, winning, losing);
		}
	}

	private static void setIny(int c, int g, int i) {
		if (c == 9) {
			if (g > i)
				winning++;
			else
				losing++;
			return;
		}

		for (int j = 1; j < 19; j++) {
			if (!card[j]) {
				card[j] = true;
				if (gyu[c] > j)
					setIny(c + 1, g + gyu[c] + j, i);
				else
					setIny(c + 1, g, i + gyu[c] + j);
				card[j] = false;
			}
		}

	}
}