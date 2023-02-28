import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1946 간단한 압축 풀기

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			bw.write('#' + String.valueOf(t + 1));
			bw.newLine();
			int total = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int cnt = Integer.parseInt(st.nextToken());

				for (int j = 0; j < cnt; j++) {
					bw.write(c);
					total++;
					if (total == 10) {
						total = 0;
						bw.newLine();
					}
				}
			}
			bw.newLine();
		}

		bw.close();
	}
}
