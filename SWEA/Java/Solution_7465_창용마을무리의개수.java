import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 7465 창용 마을 무리의 개수

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M;
	static int[][] relation;
	static boolean[] isRoot; // root -> true
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			relation = new int[N + 1][2]; // parent address, depth
			for (int i = 0; i <= N; i++) {
				relation[i][0] = i;
				relation[i][1] = 1;
			}

			isRoot = new boolean[N + 1];
			check = new boolean[N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			// 관계 파악
			for (int i = 1; i <= N; i++) {
				if (!check[i]) {
					setRelation(i);
				}
			}

			bw.write("#" + String.valueOf(t + 1) + " ");
			int total = 0;
			for (int i = 1; i <= N; i++) {
				if (isRoot[i]) {
					total++;
				}
			}
			bw.write(String.valueOf(total));
			bw.newLine();

//			for (int i = 1; i <= N; i++) {
//				System.out.println(i + "번 노드의 부모는 " + relation[i][0] + ", 트리 깊이는 " + relation[i][1]);
//			}
		}

		bw.close();
	}

	public static void setRelation(int num) {
		check[num] = true;

		if (relation[num][0] == num) {
			isRoot[num] = true;
			return;
		} else {
			setRelation(relation[num][0]);
		}
	}

	public static int findSet(int num) {
		if (relation[num][0] == num) {
			return num;
		} else {
			return findSet(relation[num][0]);
		}
	}

	public static void union(int num1, int num2) {
		int par1 = findSet(num1);
		int par2 = findSet(num2);

		if (par1 == par2)
			return;

		if (relation[par1][1] == relation[par2][1]) {
			relation[par2][0] = par1;
			relation[par1][1]++;
		} else {
			if (relation[par1][1] > relation[par2][1])
				relation[par2][0] = par1;
			else
				relation[par1][0] = par2;
		}

	}

}