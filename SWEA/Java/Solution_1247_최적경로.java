import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, A;
	static int startX, startY, endX, endY;
	static int[] clientX;
	static int[] clientY;
	static int[] clientSeq;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			clientX = new int[N];
			clientY = new int[N];
			clientSeq = new int[N];
			isSelected = new boolean[N];

			A = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());

			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				clientX[i] = Integer.parseInt(st.nextToken());
				clientY[i] = Integer.parseInt(st.nextToken());
			}

			setClient(0);

			System.out.printf("#%d %d\n", t + 1, A);
		}
	}

	public static void setClient(int pos) {
		if (pos == N) {
			calDistance();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				clientSeq[pos] = i;
				setClient(pos + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void calDistance() {
		
		int total = Math.abs(startX - clientX[clientSeq[0]]) + Math.abs(startY - clientY[clientSeq[0]]);
		for (int i = 1; i < N; i++) {
			total += Math.abs(clientX[clientSeq[i - 1]] - clientX[clientSeq[i]]);
			total += Math.abs(clientY[clientSeq[i - 1]] - clientY[clientSeq[i]]);
		}
		total += Math.abs(clientX[clientSeq[N - 1]] - endX);
		total += Math.abs(clientY[clientSeq[N - 1]] - endY);
		
		if (total < A)
			A = total;
	}

}