import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1149 RGB거리

class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());

		int[][] cost = new int[N][3];
		int[][] price = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 3; i++) {
			price[0][i] = cost[0][i];
		}
		for (int i = 1; i < N; i++) {
			price[i][0] = Math.min(price[i - 1][1], price[i - 1][2]) + cost[i][0];
			price[i][1] = Math.min(price[i - 1][0], price[i - 1][2]) + cost[i][1];
			price[i][2] = Math.min(price[i - 1][0], price[i - 1][1]) + cost[i][2];
		}

		System.out.print(Math.min(price[N - 1][0], Math.min(price[N - 1][1], price[N - 1][2])));
	}
}
