import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 1753 최단경로

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static class Edge implements Comparable<Edge> {
		int node;
		int weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException { // main method
		final int INF = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(br.readLine());

		boolean[] isVisited = new boolean[V + 1];
		ArrayList<Edge>[] connection = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			connection[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			connection[u].add(new Edge(v, w));
		}

		int[] answer = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			answer[i] = INF; // not connected
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(S, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			int n = cur.node;
			int w = cur.weight;

			if (answer[n] < w) {
				continue;
			}

			isVisited[n] = true;
			answer[n] = w;

			for (int i = 0; i < connection[n].size(); i++) {
				Edge nxt = connection[n].get(i);
				if (answer[n] + nxt.weight < answer[nxt.node]) {
					pq.add(new Edge(nxt.node, answer[n] + nxt.weight));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (answer[i] == INF)
				bw.write("INF");
			else
				bw.write(String.valueOf(answer[i]));
			bw.newLine();
		}

		bw.close();
	}

}
