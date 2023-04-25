import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, m, r;
	static int[][] origin;
	static int[][] answer;
	static boolean rotated;

	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		origin = new int[100][100];
		answer = new int[100][100];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		rotated = false;
		for (int t = 0; t < r; t++) {
			int current = Integer.parseInt(st.nextToken());

			switch (current) {
			case 1:
				cal1();
				break;
			case 2:
				cal2();
				break;
			case 3:
				cal3();
				rotated();
				break;
			case 4:
				cal4();
				rotated();
				break;
			case 5:
				cal5();
				break;
			case 6:
				cal6();
				break;
			}
			swap();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m - 1; j++) {
				bw.write(String.valueOf(answer[i][j]));
				bw.write(' ');
			}
			bw.write(String.valueOf(answer[i][m - 1]));
			bw.newLine();
		}

		bw.close();
	}

	public static void swap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				origin[i][j] = answer[i][j];
			}
		}
	}

	public static void rotated() {
		int tmp = n;
		n = m;
		m = tmp;
	}

	public static void cal1() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer[n - 1 - i][j] = origin[i][j];
			}
		}
	}

	public static void cal2() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer[i][m - 1 - j] = origin[i][j];
			}
		}
	}

	public static void cal3() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer[j][n - 1 - i] = origin[i][j];
			}
		}
	}

	public static void cal4() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer[m - 1 - j][i] = origin[i][j];
			}
		}
	}

	public static void cal5() {
		moveBlock(0, 0, 0, m / 2);
		moveBlock(0, m / 2, n / 2, m / 2);
		moveBlock(n / 2, m / 2, n / 2, 0);
		moveBlock(n / 2, 0, 0, 0);
	}

	public static void cal6() {
		moveBlock(0, 0, n / 2, 0);
		moveBlock(n / 2, 0, n / 2, m / 2);
		moveBlock(n / 2, m / 2, 0, m / 2);
		moveBlock(0, m / 2, 0, 0);
	}

	public static void moveBlock(int x1, int y1, int x2, int y2) {
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				answer[x2 + i][y2 + j] = origin[x1 + i][y1 + j];
			}
		}
	}
}
