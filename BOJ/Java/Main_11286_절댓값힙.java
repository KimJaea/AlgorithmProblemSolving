import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, x, c;
	static PriorityQueue<Integer> pqn = new PriorityQueue<>(Comparator.reverseOrder());
	static PriorityQueue<Integer> pqp = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (!pqn.isEmpty()) {
					if (!pqp.isEmpty()) {
						if (Math.abs(pqn.peek()) <= pqp.peek()) {
							bw.write(String.valueOf(pqn.poll()));
							bw.newLine();
						} else {
							bw.write(String.valueOf(pqp.poll()));
							bw.newLine();
						}
					} else {
						bw.write(String.valueOf(pqn.poll()));
						bw.newLine();
					}
				} else {
					if (!pqp.isEmpty()) {
						bw.write(String.valueOf(pqp.poll()));
						bw.newLine();
					} else {
						bw.write("0");
						bw.newLine();
					}
				}
			} else {
				if (x < 0) {
					pqn.add(x);
				} else {
					pqp.add(x);
				}
			}
		}

		bw.close();
	}
}
