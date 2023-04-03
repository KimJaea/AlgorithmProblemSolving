import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 9205 맥주 마시면서 걸어가기

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n;
	static Pos[] location;
	static boolean visited[];
	static boolean able;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			n = Integer.parseInt(br.readLine());
			location = new Pos[n + 2];
			visited = new boolean[n + 2];

			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				location[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			able = false;
			visited[0] = true;

			dfs(0);
			if (able)
				bw.write("happy\n");
			else
				bw.write("sad\n");

		}
		bw.close();
	}

	public static void dfs(int cur) {
		int cx = location[cur].x;
		int cy = location[cur].y;
		if (cx == location[n + 1].x && cy == location[n + 1].y) {
			able = true;
			return;
		}

		for (int i = 1; i <= n + 1; i++) {
			if (able)
				return;
			if (visited[i])
				continue;
			if (distance(cx, cy, location[i].x, location[i].y) <= 1000) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

	public static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
