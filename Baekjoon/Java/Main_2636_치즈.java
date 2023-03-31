import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2636 치즈

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M;
	static boolean[][] grid;

	static int answerTime, answerSize;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (st.nextToken().equals("1"))
					grid[i][j] = true;
			}
		}

		int curSize = countSize();
		while (curSize > 0) {
			answerSize = curSize;
			answerTime++;

			declineSize();
			curSize = countSize();
		}

		System.out.println(answerTime);
		System.out.print(answerSize);
	}

	private static int countSize() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j])
					total++;
			}
		}
		return total;
	}

	private static void declineSize() {
		Queue<Pos> exposure = new LinkedList<>();

		boolean[][] visited = new boolean[N][M];
		Queue<Pos> Air = new LinkedList<>();
		visited[0][0] = true;
		Air.add(new Pos(0, 0));
		while (!Air.isEmpty()) {
			Pos cur = Air.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (visited[nx][ny])
					continue;

				if (grid[nx][ny]) {
					exposure.add(new Pos(nx, ny));
				} else {
					visited[nx][ny] = true;
					Air.add(new Pos(nx, ny));
				}
			}
		}

		while (!exposure.isEmpty()) {
			grid[exposure.peek().x][exposure.peek().y] = false;
			exposure.poll();
		}
	}

}
