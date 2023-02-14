import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N;
	static String[] tree;

	static boolean calable;

	public static void main(String[] args) throws NumberFormatException, IOException {

		for (int t = 0; t < 10; t++) {
			N = Integer.parseInt(br.readLine());

			tree = new String[201];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int cur = Integer.parseInt(st.nextToken());
				tree[cur] = (st.nextToken());
			}

			calable = true;
			checkTree(1);
			
			System.out.printf("#%d ", t + 1);
			if (calable)
				System.out.println(1);
			else
				System.out.println(0);

		}

	}

	public static String checkTree(int cur) {
		if (!calable)
			return null;

		if (cur * 2 >= 200)
			return tree[cur];

		if (tree[cur * 2] == null && tree[cur * 2 + 1] == null)
			return tree[cur];

		if (tree[cur * 2] == null || tree[cur * 2 + 1] == null) {
			calable = false;
			return null;
		}

		String a = checkTree(cur * 2);
		String b = checkTree(cur * 2 + 1);

		if (a == null || a.equals("+") || a.equals("-") || a.equals("*") || a.equals("/")) {
			calable = false;
			return null;
		}
		if (b == null || b.equals("+") || b.equals("-") || b.equals("*") || b.equals("/")) {
			calable = false;
			return null;
		}

		switch (tree[cur]) {
		case "+":
			return String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
		case "-":
			return String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
		case "*":
			return String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
		case "/":
			return String.valueOf(Double.parseDouble(a) / Double.parseDouble(b));
		}
		calable = false;
		return null;
	}
}
