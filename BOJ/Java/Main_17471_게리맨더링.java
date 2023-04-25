import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 17471 게리맨더링

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static boolean dividable = false;
	static int N, answer;
	static int[] population;
	static boolean[] area; // true: 1, false: 2
	static boolean[][] connection;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());

		population = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		area = new boolean[N + 1];

		connection = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cycle = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cycle; j++) {
				int jj = Integer.parseInt(st.nextToken());
				connection[i][jj] = true;
				connection[jj][i] = true;
			}
		}

		answer = Integer.MAX_VALUE;
		setArea(2); // 1번 구역은 무조건 false

		if (!dividable)
			bw.write("-1");
		else
			bw.write(String.valueOf(answer));
		bw.close();
	}

	public static void setArea(int num) { // 구역 분류
		if (num == N + 1) {
			int firstTrue = -1;
			for (int i = 2; i <= N; i++) {
				if (area[i]) {
					firstTrue = i;
					i = N;
				}
			}
			if (firstTrue == -1)
				return;

			if (votable(firstTrue)) {
				dividable = true;
				int dif = calDif();
				answer = Math.min(answer, dif);
			}
			return;
		}

		area[num] = false;
		setArea(num + 1);
		area[num] = true;
		setArea(num + 1);
	}

	private static boolean votable(int firstTrue) {
		boolean[] visited = new boolean[N + 1];

		Queue<Integer> queue = new LinkedList<>();
		visited[1] = true;
		queue.add(1);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (connection[cur][i] && !visited[i] && !area[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}

		visited[firstTrue] = true;
		queue.add(firstTrue);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (connection[cur][i] && !visited[i] && area[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	private static int calDif() {
		int countTrue = 0, countFalse = 0;
		for (int i = 1; i <= N; i++) {
			if (area[i])
				countTrue += population[i];
			else
				countFalse += population[i];
		}
		return Math.abs(countTrue - countFalse);
	}
}
