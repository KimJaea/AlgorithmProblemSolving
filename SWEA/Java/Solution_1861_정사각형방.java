import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, cur_room, max_room, max_count;
	static int[][] room;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			room = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max_room = Integer.MAX_VALUE;
			max_count = Integer.MIN_VALUE;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cur_room = room[i][j];
					moveRoom(i, j, 1);
				}
			}
			System.out.printf("#%d %d %d\n", t + 1, max_room, max_count);
		}

	}

	public static void moveRoom(int x, int y, int cnt) {
		if (cnt > max_count) {
			max_room = cur_room;
			max_count = cnt;
		} else if (cnt == max_count) {
			if (max_room > cur_room) {
				max_room = cur_room;
			}
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			if (room[nx][ny] == room[x][y] + 1) {
				moveRoom(nx, ny, cnt + 1);
				i = 4;
			}
		}
	}
}
