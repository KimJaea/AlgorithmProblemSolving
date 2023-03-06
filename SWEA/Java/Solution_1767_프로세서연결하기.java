import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1767 프로세서 연결하기

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, max_core, min_line;
	static int[][] grid; // 1: core, 0: empty

	static ArrayList<Pos> cores;

	// R D L U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];

			max_core = 0;
			min_line = 0;

			// int connected = 0;
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							// connected++;
						} else {
							cores.add(new Pos(i, j));
						}
					}

				}
			}

			connectCore(0, 0);

			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(min_line));
			bw.newLine();
		}
		bw.close();
	}

	public static void connectCore(int pos, int core) {
		if (pos == cores.size()) {
			if (core >= max_core) {
				int total = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (grid[i][j] == 2)
							total++;
					}
				}

				if (core == max_core) {
					if (total < min_line)
						min_line = total;
				} else {
					max_core = core;
					min_line = total;
				}
			}
			return;
		}
        
		connectCore(pos + 1, core);

		Pos p = cores.get(pos);
		for (int d = 0; d < 4; d++) {
			if (connectAble(p, d)) {
				int px = p.x + dx[d];
				int py = p.y + dy[d];
				while (px >= 0 && px < N && py >= 0 && py < N) {
					grid[px][py] = 2;
					px += dx[d];
					py += dy[d];
				}

				connectCore(pos + 1, core + 1);

				px -= dx[d];
				py -= dy[d];
				while (px != p.x || py != p.y) {
					grid[px][py] = 0;
					px -= dx[d];
					py -= dy[d];
				}
			}
		}
	}

	public static boolean connectAble(Pos p, int d) {
		int px = p.x + dx[d];
		int py = p.y + dy[d];
		while (px >= 0 && px < N && py >= 0 && py < N) {
			if (grid[px][py] != 0) {
				return false;
			}
			px += dx[d];
			py += dy[d];
		}
		return true;
	}

}
