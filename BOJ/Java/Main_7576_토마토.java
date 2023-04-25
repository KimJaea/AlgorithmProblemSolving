import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 7576 토마토

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int M, N;
	static int[][] box;
	static boolean[][] checked;
	static Queue<Pos> tomato = new LinkedList<Pos>();

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		checked = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					tomato.add(new Pos(i, j));
					checked[i][j] = true;
				} else if (box[i][j] == -1) {
					checked[i][j] = true;
				}
			}
		}

		int day = 0;
		while (!tomato.isEmpty()) {
			int size = tomato.size();
			while (size-- > 0) {
				Pos cur = tomato.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						continue;
					if (checked[nx][ny])
						continue;
					tomato.add(new Pos(nx, ny));
					checked[nx][ny] = true;
				}
			}
			day++;
		}
		day--;
		
		if (allRipen()) {
			System.out.print(day);
		} else {
			System.out.print(-1);
		}
	}

	public static boolean allRipen() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!checked[i][j])
					return false;
			}
		}
		return true;
	}

}