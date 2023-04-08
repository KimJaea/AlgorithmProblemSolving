import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2383 점심 식사시간

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Str extends Pos {
	int l;

	public Str(int x, int y, int l) {
		super(x, y);
		this.l = l;
	}
}

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, S, A;
	static int[][] grid;

	static Str[] stairs; // 계단 좌표 및 길이 저장
	static Pos[] people; // 사람 위치 저장
	static boolean[] goFirst; // 사람이 어떤 계단에 갈 지 저장

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];

			S = 0; // 계단의 수 (2)
			M = 0; // 사람의 수 (~10)
			stairs = new Str[2];
			people = new Pos[10];
			goFirst = new boolean[10];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());

					if (grid[i][j] == 1) {
						people[M] = new Pos(i, j);
						M++;
					} else if (grid[i][j] > 1) {
						stairs[S] = new Str(i, j, grid[i][j]);
						S++;
					}
				}
			}

			A = Integer.MAX_VALUE; // 최단 시간
			setStair(0);

			// 가장 빨리 내려가는 경우 출력

			bw.write("#" + String.valueOf(t) + " ");
			bw.write(String.valueOf(A) + "\n");
		}
		bw.close();
	}

	public static void setStair(int cur) {
		if (cur == M) {
			movePeople();
			return;
		}

		goFirst[cur] = false; //
		setStair(cur + 1);
		goFirst[cur] = true;
		setStair(cur + 1);
	}

	public static void movePeople() {
		Queue<Pos> move = new LinkedList<>(); // Pair 역할을 하는 Pos, 거리와 계단 번호
		Queue<Integer> s1 = new LinkedList<Integer>(); // 1번 계단
		Queue<Integer> s2 = new LinkedList<Integer>(); // 2번 계단

		for (int i = 0; i < M; i++) {
			if (goFirst[i]) { // 1번 계단으로 이동
				int dis = Math.abs(stairs[0].x - people[i].x);
				dis += Math.abs(stairs[0].y - people[i].y);
				move.add(new Pos(dis, 1));
			} else { // 2번 계단으로 이동
				int dis = Math.abs(stairs[1].x - people[i].x);
				dis += Math.abs(stairs[1].y - people[i].y);
				move.add(new Pos(dis, 2));
			}
		}

		int time = 1, sz = 0;

		while (!move.isEmpty() || !s1.isEmpty() || !s2.isEmpty()) {
			time++;
			sz = s1.size();
			while (sz-- > 0) { // 1번 계단 내려가기
				int c = s1.poll();
				if (--c > 0) {
					s1.add(c);
				}
			}
			sz = s2.size();
			while (sz-- > 0) { // 2번 계단 내려가기
				int c = s2.poll();
				if (--c > 0) {
					s2.add(c);
				}
			}

			if (s1.isEmpty() && s2.isEmpty() && move.isEmpty())
				break;

			sz = move.size();
			while (sz-- > 0) { // 계단으로 이동하기
				Pos c = move.poll();
				c.x--;
				if (c.x <= 0) {
					if (c.y == 1) { // 1번 계단
						if (s1.size() < 3) {
							s1.add(stairs[0].l);
						} else {
							move.add(c);
						}
					} else { // 2번 계단
						if (s2.size() < 3) {
							s2.add(stairs[1].l);
						} else {
							move.add(c);
						}
					}
				} else {
					move.add(c);
				}
			}

			if (time > A)
				return;
		}

		A = Math.min(A, time);
	}

}