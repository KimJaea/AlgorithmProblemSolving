import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1238 Contact
// DFS가 아닌 BFS

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException { // main method

		for (int t = 0; t < 1; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());

			boolean[] isVisited = new boolean[101];
			boolean[][] callbook = new boolean[101][101];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				callbook[start][end] = true;
			}

			int answer = 0;

			Queue<Integer> queue = new LinkedList<>();
			isVisited[S] = true;
			queue.offer(S);

			while (!queue.isEmpty()) {
				int size = queue.size();
				answer = 0;

				while (size-- > 0) {
					int cur = queue.poll();
					answer = Math.max(answer, cur);
					for (int i = 1; i <= 100; i++) {
						if (callbook[cur][i] && !isVisited[i]) {
							isVisited[i] = true;
							queue.offer(i);
						}
					}
				}

			}

			bw.write('#' + String.valueOf(t + 1) + ' ' + String.valueOf(answer));
			bw.newLine();
		}

		bw.close();
	}
}
