import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Camera {
	int x;
	int y;
	int t; // type
	int d; // direction

	// R D L U
	// 8 4 2 1

	public Camera(int x, int y, int t) {
		super();
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, cameraCount, answer;
	static int[][] office;

	static Camera[] camera;
	static int[] direction;

	// R D L U
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		camera = new Camera[8];
		direction = new int[8];
		cameraCount = 0;

		office = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());

				if (office[i][j] > 0 && office[i][j] < 6) {
					camera[cameraCount++] = new Camera(i, j, office[i][j]);
				}
			}
		}

		answer = N * M;
		setDirection(0);
		bw.write(String.valueOf(answer));
		bw.close();
	}

	public static void setDirection(int pos) {
		if (pos == cameraCount) {
			CCTV();
			return;
		}

		switch (camera[pos].t) {
		case 1:
			for (int i = 0; i < 4; i++) {
				camera[pos].d = 1 << i;
				setDirection(pos + 1);
			}
			break;
		case 2:
			camera[pos].d = 1 + (1 << 2);
			setDirection(pos + 1);
			camera[pos].d = (1 << 1) + (1 << 3);
			setDirection(pos + 1);
			break;
		case 3:
			camera[pos].d = 1 + (1 << 3);
			setDirection(pos + 1);
			camera[pos].d = 1 + (1 << 1);
			setDirection(pos + 1);
			for (int i = 1; i < 3; i++) {
				camera[pos].d = camera[pos].d << 1;
				setDirection(pos + 1);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				camera[pos].d = (1 << 4) - 1 - (1 << i);
				setDirection(pos + 1);
			}
			break;
		case 5:
			camera[pos].d = (1 << 4) - 1;
			setDirection(pos + 1);
			break;
		}
	}

	public static void CCTV() {
		int[][] copied = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copied[i][j] = office[i][j];
			}
		}

		for (int i = 0; i < cameraCount; i++) {
			for (int j = 0; j < 4; j++) { // R D L U
				int cx = camera[i].x;
				int cy = camera[i].y;
				int dir = camera[i].d;

				if ((dir & 1 << j) > 0) {
					while (cx >= 0 && cx < N && cy >= 0 && cy < M) {
						if (copied[cx][cy] == 6)
							break;
						if (copied[cx][cy] == 0)
							copied[cx][cy] = 7;

						cx += dx[j];
						cy += dy[j];
					}
				}
			}

		}

		int total = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copied[i][j] == 0)
					total++;
			}
		}

		answer = Math.min(answer, total);
	}
}