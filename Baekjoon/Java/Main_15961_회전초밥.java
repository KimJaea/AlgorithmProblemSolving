import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 15961 회전 초밥

public class Main {
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, d, k, c;
	static int[] sushi;
	static int[] ate;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		ate = new int[d + 1];

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		int cur = 1;
		ate[c]++;

		int srt = 0;
		int end = 0;
		while (end < k) {
			ate[sushi[end]]++;
			if (ate[sushi[end]] == 1)
				cur++;
			end++;
		}
		
		int a = cur;
		for (int i = 0; i < N; i++) {
			ate[sushi[srt]]--;
			if (ate[sushi[srt]] == 0)
				cur--;
			srt++;
			ate[sushi[end]]++;
			if (ate[sushi[end]] == 1)
				cur++;
			end++;
			if (end >= N)
				end -= N;

			a = Math.max(a, cur);
		}

		System.out.print(a);
	}
}
