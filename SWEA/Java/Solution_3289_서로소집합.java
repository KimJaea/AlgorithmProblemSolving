import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 3289 서로소 집합

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, m;
	static int[][] tree;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			tree = new int[n + 1][2]; // parent address, depth
			for (int i = 0; i <= n; i++) {
				tree[i][0] = i;
				tree[i][1] = 1;
			}

			bw.write("#" + String.valueOf(t + 1) + " ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int cal = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (cal == 0) { // 합치기
					union(a, b);
				} else { // 조회하기
					if (findSet(a) == findSet(b)) {
						bw.write("1");
					} else {
						bw.write("0");
					}
				}
			}
			bw.newLine();

//			for (int i = 1; i <= n; i++) {
//				System.out.println(i + "번 노드의 부모는 " + tree[i][0] + ", 트리 깊이는 " + tree[i][1]);
//			}
		}

		bw.close();
	}

	public static int findSet(int num) {
		if (tree[num][0] == num)
			return num;
		else
			return findSet(tree[num][0]);
	}

	public static void union(int num1, int num2) {
		int par1 = findSet(num1);
		int par2 = findSet(num2);

		if (par1 == par2)
			return;
		
		if (tree[par1][1] == tree[par2][1]) {
			//System.out.println(par1 + "에 " + par2 + "합치기...");
			//System.out.println(tree[par1][1] + "깊이 였기에 여기에 " + tree[par2][1] + "추가 ");
			tree[par2][0] = par1;
			tree[par1][1]++;
		} else {
			if (tree[par1][1] > tree[par2][1])
				tree[par2][0] = par1;
			else
				tree[par1][0] = par2;
		}

	}

}