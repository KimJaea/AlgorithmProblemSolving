import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M;
	static boolean able = false;
	static boolean[] visited;
	static ArrayList<Integer>[] friendship;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N];
		friendship = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			friendship[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friendship[a].add(b);
			friendship[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			visited[i] = true;
			relationship(i, 1);
			visited[i] = false;

			if (able)
				break;
		}

		if (able)
			bw.write("1");
		else
			bw.write("0");
		bw.close();
	}

	public static void relationship(int num, int cnt) {
		if (able)
			return;
        
		if (cnt >= 5) {	
			able = true;
			return;
		}

		for (int i = 0; i < friendship[num].size(); i++) {
			int cur = friendship[num].get(i);
			if (!visited[cur]) {
				visited[cur] = true;
				relationship(cur, cnt + 1);
				visited[cur] = false;
			}
		}
	}
}