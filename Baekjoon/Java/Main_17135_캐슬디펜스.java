import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 17135 캐슬 디펜스

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int[] archer;
	static boolean[] isSelected;
	static int[][] grid;

	static int N, M, D, maxKilled;
	static Queue<Enemy> enemies = new LinkedList<>();

	static class Enemy {
		int x;
		int y;

		public Enemy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		grid = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxKilled = 0;
		archer = new int[3];
		isSelected = new boolean[M];
		setArcher(0, 0);

		bw.write(String.valueOf(maxKilled));
		bw.close();
	}

	public static void setArcher(int cnt, int pos) {
		if (cnt == 3) {
			int p = 0;
			for (int i = 0; i < M; i++) {
				if (isSelected[i])
					archer[p++] = i;
			}

			killEnemy();
			return;
		}

		for (int i = pos; i < M; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				setArcher(cnt + 1, i + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void killEnemy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					enemies.offer(new Enemy(i, j));
				}
			}
		}

		int killed = 0;
		int cycle = 0;

		while (!enemies.isEmpty()) {
			Enemy[] willKill = new Enemy[3];
			for (int i = 0; i < 3; i++)
				willKill[i] = new Enemy(-1, M);
			int[] distance = { D + 1, D + 1, D + 1 };

			cycle = enemies.size();
			while (cycle-- > 0) {
				Enemy enemy = enemies.poll();
				for (int i = 0; i < 3; i++) {
					int dis = Math.abs(enemy.x - N) + Math.abs(enemy.y - archer[i]);
					if (dis > D)
						continue;

					if (dis < distance[i]) {
						distance[i] = dis;
						willKill[i] = enemy;
					}
					if ((dis == distance[i] && enemy.y < willKill[i].y)) {
						distance[i] = dis;
						willKill[i] = enemy;
					}
				}
				enemies.offer(enemy);
			}

			cycle = enemies.size();
			while (cycle-- > 0) {
				Enemy enemy = enemies.poll();
				boolean save = true;
				for (int i = 0; i < 3; i++) {
					if (enemy.x == willKill[i].x && enemy.y == willKill[i].y)
						save = false;
				}
				if (save)
					enemies.offer(enemy);
				else
					killed++;
			}

			// 이동
			cycle = enemies.size();
			while (cycle-- > 0) {
				Enemy enemy = enemies.poll();
				enemy.x++;
				if (enemy.x < N)
					enemies.offer(enemy);
			}

		}

		maxKilled = Math.max(killed, maxKilled);
	}
}
