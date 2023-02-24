import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1206 View

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, answer;
	static int[] building;

	public static void main(String[] args) throws IOException {
		for (int t = 0; t < 10; t++) {
			N = Integer.parseInt(br.readLine());
			building = new int[N + 4];

			st = new StringTokenizer(br.readLine());
			for (int i = 2; i < N + 2; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}

			answer = 0;
			for (int i = 2; i < N + 2; i++) {
				int maxHeight = Math.max(building[i - 2], building[i - 1]);
				maxHeight = Math.max(maxHeight, Math.max(building[i + 1], building[i + 2]));
				int view = building[i] - maxHeight;
				if (view > 0)
					answer += view;
			}

			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(answer));
			bw.newLine();
		}

		bw.close();
	}

}
