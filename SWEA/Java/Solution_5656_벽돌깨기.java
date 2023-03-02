import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 5656 벽돌 깨기

class Pos {
	int x;
	int y;
	int b;

	public Pos(int x, int y, int b) {
		super();
		this.x = x;
		this.y = y;
		this.b = b;
	}
}

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st = null; 

	static int N, W, H, answer;
	static int[][] origin;

	// R D L U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			origin = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer = H * W;
			setShootPoint(origin, 0);

			bw.write('#' + String.valueOf(t + 1));
			bw.write(' ' + String.valueOf(answer));
			bw.newLine();
		}

		bw.close();
	}

	public static int countBlocks(int[][] grid) {
		int total = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (grid[i][j] > 0)
					total++;
			}
		}

		return total;
	}

	public static void setShootPoint(int[][] bef, int cnt) {
		if (cnt == N) {
			int total = countBlocks(bef);
			answer = Math.min(answer, total);
			return;
		}

		int[][] cur = new int[H][W];

		for (int w = 0; w < W; w++) { // 삭제할 열 선택
			Queue<Pos> queue = new LinkedList<Pos>();

			// cur 배열에 복사
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					cur[i][j] = bef[i][j];
				}
			}

			// w 열 맨 위 벽돌 선택
			for (int i = 0; i < H; i++) {
				if (cur[i][w] > 0) {
					queue.add(new Pos(i, w, cur[i][w]));
					cur[i][w] = 0;
					i = H;
				}
			}

			// 선택된 벽돌로 인한 연쇄 반응
			while (!queue.isEmpty()) {
				int x = queue.peek().x;
				int y = queue.peek().y;
				int b = queue.peek().b;
				queue.poll();

				// 상하좌우로 size-1만큼 삭제하면서 0보다크면 add
				for (int s = 1; s < b; s++) {
					for (int d = 0; d < 4; d++) {
						int nx = x + (dx[d] * s);
						int ny = y + (dy[d] * s);
						if (nx < 0 || nx >= H || ny < 0 || ny >= W)
							continue;
						if (cur[nx][ny] > 0) {
							queue.add(new Pos(nx, ny, cur[nx][ny]));
							cur[nx][ny] = 0;
						}
					}
				}
			}

			// 사라진 공간에 벽돌 내려오기 - cur
			gravityBrick(cur);
			setShootPoint(cur, cnt + 1);
		}

	}

	public static void gravityBrick(int[][] grid) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (grid[j][i] > 0) {
					queue.add(grid[j][i]);
					grid[j][i] = 0;
				}
			}
			int j = H - 1;
			while (!queue.isEmpty()) {
				grid[j][i] = queue.poll();
				j--;
			}
		}
	}
}
