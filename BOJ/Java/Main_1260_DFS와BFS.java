import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1260 DFS와 BFS

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, V;
	static boolean[][] edge;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		edge = new boolean[N + 1][N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edge[a][b] = true;
			edge[b][a] = true;
		}

		isVisited = new boolean[N + 1];
		dfs(V);
		bw.newLine();
		isVisited = new boolean[N + 1];
		bfs(V);
		bw.close();
	}

	public static void dfs(int v) throws IOException {
		isVisited[v] = true;
		bw.write(String.valueOf(v) + " ");
		for (int i = 0; i <= N; i++) {
			if (edge[v][i] && !isVisited[i]) {
				dfs(i);
			}
		}
	} // dfs

	public static void bfs(int v) throws IOException {
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(v);
		isVisited[v] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			bw.write(String.valueOf(cur) + " ");
			
			for (int i = 0; i <= N; i++) {
				if (edge[cur][i] && !isVisited[i]) {
					queue.offer(i);
					isVisited[i] = true;
				}
			}
		}
	} // bfs

}
