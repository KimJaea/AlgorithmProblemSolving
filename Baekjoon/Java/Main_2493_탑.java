import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Stack;

class Pair {
	int n;
	int h;

	Pair(int n, int h) {
		this.n = n;
		this.h = h;
	}
}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static Stack<Pair> tower = new Stack<>();

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		bw.write("0 ");
		tower.add(new Pair(1, Integer.parseInt(st.nextToken())));

		for (int i = 1; i < n - 1; i++) {
			Pair p = new Pair(i + 1, Integer.parseInt(st.nextToken()));

			while (!tower.isEmpty() && tower.peek().h < p.h) {
				tower.pop();
			}
			if (tower.isEmpty()) {
				bw.write("0 ");
				tower.add(p);
			} else {
				bw.write(String.valueOf(tower.peek().n) + " ");
				tower.push(p);
			}
		}

		int cur = Integer.parseInt(st.nextToken());
		while (!tower.isEmpty() && tower.peek().h < cur) {
			tower.pop();
		}
		if (tower.isEmpty()) {
			bw.write("0 ");
		} else {
			bw.write(String.valueOf(tower.peek().n));
		}

		bw.close();
	}

}
