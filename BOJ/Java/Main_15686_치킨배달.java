import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, hc, cc, answer;
	// static int[][] city;
	static int[][] house; // 집 위치
	static int[][] chicken; // 치킨 위치
	static boolean[] isSelected; // 치킨 선택 여부
	static int[] selected; // 고른 치킨 집 번호

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// city = new int[N][N];
		house = new int[N * 2][2];
		chicken = new int[13][2];
		isSelected = new boolean[13];
		selected = new int[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// city[i][j] = Integer.parseInt(st.nextToken());
				int cur = Integer.parseInt(st.nextToken());
				
				if (cur == 1) {
					house[hc][0] = i;
					house[hc][1] = j;
					//System.out.println(house[hc][0]+", "+house[hc][1]);//
					hc++;
				} else if (cur == 2) {
					chicken[cc][0] = i;
					chicken[cc][1] = j;
					cc++;
				}
				
			}
		}

		answer = Integer.MAX_VALUE;
		selectChicken(0, 0);
		
		System.out.print(answer);

	}

	public static void selectChicken(int pos, int start) {
		if (pos == M) {
			calDistance();
			return;
		}
		
		for (int i = start; i < cc; i++) {
			if (isSelected[i])
				continue;
			selected[pos] = i;
			selectChicken(pos + 1, i + 1);
		}
	}

	public static void calDistance() {
		int total = 0;
		
		for (int i = 0; i < hc; i++) {
			int dis = Integer.MAX_VALUE;
			
			for (int j = 0; j < M; j++) {
				int cnum = selected[j];
				int cur = Math.abs(house[i][0] - chicken[cnum][0]) + Math.abs(house[i][1] - chicken[cnum][1]);
				if (cur < dis)
					dis = cur;
			}
			total += dis;
		}

		if (total < answer)
			answer = total;
	}
}
