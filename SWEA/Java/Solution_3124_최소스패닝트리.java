import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

// 3124 최소 스패닝 트리

class Edge implements Comparable<Edge> {
	int first;
	int second;
	int weight;

	public Edge(int first, int second, int weight) {
		super();
		this.first = first;
		this.second = second;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
		// return this.weight - o.weight;
	}

}

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 위한 BufferedWriter 변수 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 변수 선언
	static StringTokenizer st = null; // 입력 값을 공백 기준으로 분리하기 위한 변수 선언

	static int V, E;
	static int[] tree;
	static Edge[] edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException { // main method
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				// Kruskal
				edgeList[i] = new Edge(first, second, weight);
			}

			Arrays.sort(edgeList);

			tree = new int[V + 1]; // parent
			for (int i = 1; i <= V; i++) {
				tree[i] = i; // rep is myself
			}

			int count = 0;
			long total = 0;

			for (int i = 0; i < E; i++) {
				Edge e = edgeList[i];
				if (unionSet(e.first, e.second)) {
					total += e.weight;
					count++;
				}
				if (count == V - 1)
					break;
			}

			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(total));
			bw.newLine();
		}

		bw.close();
	}

	public static int findSet(int num) {
		if (tree[num] == num)
			return num;
		else
			return tree[num] = findSet(tree[num]);
	}

	public static boolean unionSet(int a, int b) {
		int ap = findSet(a);
		int bp = findSet(b);
		if (ap == bp) {
			return false;
		} else {
			tree[bp] = ap;
			return true;
		}
	}

}
