import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 8458 원점으로 집합

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, A;
	static int[] dis;
	static int[] ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			dis = new int[N];
			ans = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				dis[i] = Math.abs(Integer.parseInt(st.nextToken()));
				dis[i] += Math.abs(Integer.parseInt(st.nextToken()));
			}

			int A = 0;
			for (int i = 0; i < N; i++) {
				int time = 0;
				int cur = dis[i];
				while (cur > 0) {
					time++;
					cur -= (time);
				}
				if (Math.abs(cur) % 2 != 0) {
					if (time % 2 == 0) {
						time++;
					} else {
						time += 2;
					}
				}
				ans[i] = time;
				A = Math.max(A, time);
			}

			boolean able = true;
			for (int i = 0; i < N; i++) {
				if (ans[i] == A)
					continue;
				int total = 0;
				for (int j = ans[i] + 1; j <= A; j++) {
					total += j;
				}
				if (total % 2 != 0) {
					able = false;
					i = N;
				}
			}

			if (!able)
				A = -1;

			bw.write("#" + String.valueOf(t));
			bw.write(" " + String.valueOf(A) + "\n");
		}
		bw.close();
	}

}
