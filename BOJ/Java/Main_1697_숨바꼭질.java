import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1697 숨바꼭질

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] load = new int[100001];

		if (N == K) {
			bw.write("0");
			bw.close();
			return;
		}

		load[N] = -1;
		Queue<Integer> queue = new ArrayDeque<>();
		if (N > 0)
			queue.offer(N - 1);
		if (N < 100000)
			queue.offer(N + 1);
		if (N * 2 <= 100000)
			queue.offer(N * 2);

		int time = 0;
		while (!queue.isEmpty()) {
			time++;
			int cycle = queue.size();
			while (cycle-- > 0) {
				int cur = queue.poll();
				load[cur] = time;
				if (cur == K) {
					while (!queue.isEmpty())
						queue.poll();
					break;
				}
				// 안 간 곳이면 0이면 offer
				if (cur > 0 && load[cur - 1] == 0)
					queue.offer(cur - 1);
				if (cur < 100000 && load[cur + 1] == 0)
					queue.offer(cur + 1);
				if (cur * 2 <= 100000 && load[cur * 2] == 0)
					queue.offer(cur * 2);
			}
		}

		bw.write(String.valueOf(load[K]));
		bw.close();
	}

}
