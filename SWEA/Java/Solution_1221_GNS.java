import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1221 GNS

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());

			int[] nums = new int[10];
			String[] language = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cnt; i++) {
				String cur = st.nextToken();
				for (int j = 0; j < 10; j++) {
					if (cur.equals(language[j])) {
						nums[j]++;
						j = 10;
					}
				}
			}

			bw.write('#' + String.valueOf(t + 1));
			bw.newLine();
			for (int i = 0; i < 10; i++) {
				String cur = language[i];
				for (int j = 0; j < nums[i]; j++) {
					bw.write(cur+" ");
				}
			}
		}

		bw.close();
	}
}
