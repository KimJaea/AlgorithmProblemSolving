import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 5643 키 순서

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, A;
	static boolean check[];
	static ArrayList<Integer> higher[];
	static ArrayList<Integer> lower[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			check = new boolean[N + 1];

			higher = new ArrayList[N + 1];
			lower = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				higher[i] = new ArrayList<>();
				lower[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				higher[a].add(b);
				lower[b].add(a);
			}

			A = 0;
			for (int i = 1; i <= N; i++) {
				Arrays.fill(check, false);
				searchUp(i);
				searchDown(i);
				if (searchedAll())
					A++;
			}

			bw.write("#" + String.valueOf(t) + " ");
			bw.write(String.valueOf(A) + "\n");
		}
		bw.close();
	}

	private static boolean searchedAll() {
		for (int i = 1; i <= N; i++) {
			if (!check[i])
				return false;
		}
		return true;
	}

	private static void searchDown(int cur) {
		check[cur] = true;
		for (int i = 0; i < lower[cur].size(); i++) {
			int nxt = lower[cur].get(i);
			if (!check[nxt]) {
				searchDown(nxt);
			}
		}
	}

	private static void searchUp(int cur) {
		check[cur] = true;
		for (int i = 0; i < higher[cur].size(); i++) {
			int nxt = higher[cur].get(i);
			if (!check[nxt]) {
				searchUp(nxt);
			}
		}
	}

}
