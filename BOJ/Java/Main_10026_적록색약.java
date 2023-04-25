import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// static StringTokenizer st = null;

	static int N, normalCount, weaknessCount;

	static char[][] normalPicture;
	static char[][] weaknessPicture;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		normalPicture = new char[N][N];
		weaknessPicture = new char[N][N];

		for (int i = 0; i < N; i++) {
			normalPicture[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				weaknessPicture[i][j] = normalPicture[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (normalPicture[i][j] != '.') {
					// System.out.println(i + ", " + j + "에서 멀쩡 그림 시작");
					normalSee(i, j, normalPicture[i][j]);
					normalCount++;
				}
				if (weaknessPicture[i][j] != '.') {
					weaknessSee(i, j, weaknessPicture[i][j]);
					weaknessCount++;
				}
			}
		}

		bw.write(String.valueOf(normalCount) + " " + String.valueOf(weaknessCount));
		bw.close();
	}

	public static void normalSee(int x, int y, char c) {
		normalPicture[x][y] = '.';
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (normalPicture[nx][ny] == c) {
				normalSee(nx, ny, c);
			}
		}
	}

	public static void weaknessSee(int x, int y, char c) {
		weaknessPicture[x][y] = '.';
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (c == 'B') {
				if (weaknessPicture[nx][ny] == 'B')
					weaknessSee(nx, ny, c);
			} else {
				if (weaknessPicture[nx][ny] == 'R' || weaknessPicture[nx][ny] == 'G')
					weaknessSee(nx, ny, c);
			}

		}
	}
}