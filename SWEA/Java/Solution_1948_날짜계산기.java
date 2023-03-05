import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1948 날짜 계산기

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int fm = Integer.parseInt(st.nextToken());
			int fd = Integer.parseInt(st.nextToken());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());

			int daydiff = 0;
			if (fm == sm) {
				daydiff = sd - fd + 1;
			} else {
				daydiff = days[fm] - fd + 2;
				fm++;
				while (fm < sm) {
					daydiff += days[fm++];
				}
				daydiff += sd - 1;
			}
			bw.write("#" + String.valueOf(t + 1));
			bw.write(" " + String.valueOf(daydiff));
			bw.newLine();
		}
		bw.close();
	}
}
