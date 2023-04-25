import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		bw.write("<");
		Queue<Integer> josephus = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			josephus.offer(i + 1);
		}
		int cur = 1;
		while (josephus.size() > 1) {
			while (cur++ < m) {
				josephus.offer(josephus.poll());
			}
			cur = 1;
			bw.write(String.valueOf(josephus.poll()));
			bw.write(", ");
		}
		bw.write(String.valueOf(josephus.poll()));

		bw.write(">");
		bw.close();
	}

}
