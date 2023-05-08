import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Stick {
	public boolean garo; // 가로로 놓여있는지에 대해 표현. true라면 가로, false라면 세로
	public int x, y; // 기둥의 중심 좌표

	public Stick() { // 기본 생성자
	}

	public Stick(boolean garo, int x, int y) { // 매개변수가 있는 생성자
		super();
		this.garo = garo;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() { // 확인을 위한 출력문
		return "Stick [garo=" + garo + ", x=" + x + ", y=" + y + "]";
	}

}

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 위한 BufferedWriter
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader

	static boolean A = false; // 정답 도출 가능 여부

	static int N; // 평지의 한 변의 길이
	static char grid[][]; // 평지 정보를 저장하는 2차원 배열
	static boolean garoCheck[][]; // 해당 좌표에 가로 형태로 방문했는지 체크
	static boolean seroCheck[][]; // 해당 좌표에 세로 형태로 방문했는지 체크

	static Stick B = new Stick(); // B 기둥의 정보를 저장하는 객체
	static Stick E = new Stick(); // E 기둥의 정보를 저장하는 객체

	static int dd[] = { -1, 0, 1 }; // 기둥의 중심을 기준으로 양옆까지 겹치는 기둥이 있는지 확인하기 위한 배열
	static int dx[] = { -1, 1, 0, 0 }; // 기둥 이동 시, x좌표를 연산하기 위한 배열
	static int dy[] = { 0, 0, -1, 1 }; // 기둥 이동 시, y좌표를 연산하기 위한 배열

	static int rx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int ry[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(br.readLine()); // 평지의 한 변의 길이 입력
		grid = new char[N][N]; // N * N 크기의 평지
		garoCheck = new boolean[N][N]; // 객체 할당
		seroCheck = new boolean[N][N]; // 객체 할당

		for (int i = 0; i < N; i++) { // 구조적으로 방문할 수 없는 곳은 가지 않도록 미리 true 처리
			garoCheck[i][0] = true; // 가로 기둥일 때 가장 왼쪽
			garoCheck[i][N - 1] = true; // 가로 기둥일 때 가장 오른쪽
			seroCheck[0][i] = true; // 세로 기둥일 때 가장 위쪽
			seroCheck[N - 1][i] = true; // 세로 기둥일 때 가장 아래쪽
		}

		int bc = 0, ec = 0; // B와 E가 등장했던 횟수 기록
		int bx = 0, by = 0, ex = 0, ey = 0; // B와 E가 처음 등장하는 곳의 좌표를 저장
		for (int i = 0; i < N; i++) { // i번째 행
			String str = br.readLine(); // i번째 입력
			for (int j = 0; j < N; j++) { // j번째 열
				grid[i][j] = str.charAt(j); // grid 배열에 평지 정보 저장

				if (grid[i][j] == 'B') { // 해당 평지 정보가 B였을 경우
					bc++; // 등장 횟수 증가
					if (bc == 1) { // 처음 발견한 경우
						bx = i; // x 좌표 기록
						by = j; // y 좌표 기록
					} else if (bc == 2) { // 두번째로 발견한 경우 (중심)
						B.x = i; // 중심의 x 좌표 기록
						B.y = j; // 중심의 y 좌표 기록
						if (bx == i) // 첫 좌표가 같은 행에 있다면
							B.garo = true; // 가로로 놓인 상태
						else // 아니라면
							B.garo = false; // 세로로 놓인 상태
					}
					grid[i][j] = '0'; // 해당 좌표에 대한 정보 제거
				}
				if (grid[i][j] == 'E') { // 해당 평지 정보가 E였을 경우
					ec++; // 등장 횟수 증가
					if (ec == 1) { // 처음 발견한 경우
						ex = i; // x 좌표 기록
						ey = j; // y 좌표 기록
					} else if (ec == 2) { // 두번째로 발견한 경우 (중심)
						E.x = i; // 중심의 x 좌표 기록
						E.y = j; // 중심의 y 좌표 기록
						if (ex == i) // 첫 좌표가 같은 행에 있다면
							E.garo = true; // 가로로 놓인 상태
						else // 아니라면
							E.garo = false; // 세로로 놓인 상태
					}
					grid[i][j] = '0'; // 해당 좌표에 대한 정보 제거
				}
			}
		}

		int count = -1; // 현재 이동한 횟수 저장, BFS 수행 전에 증가시킬 예정이므로 -1로 시작

		Queue<Stick> queue = new LinkedList<>(); // 기둥을 움직이는 과정을 저장하는 Queue 객체
		queue.add(B); // B부터 확인하기 위해 queue에 add
		if (B.garo) // 해당 좌표에 가로로 방문했다면
			garoCheck[B.x][B.y] = true; // garoCheck에 기록
		else // 세로로 방문했다면
			seroCheck[B.x][B.y] = true; // seroCheck에 기록

		while (!queue.isEmpty()) { // 배열이 비기 전(모든 가능한 경우의 수를 확인하기 전)까지 반복
			count++; // 이동 횟수 증가
			int size = queue.size(); // 현재 queue의 크기 저장, count번 이동했을 때의 Stick을 확인하기 위함
			while (size-- > 0) { // 확인한 queue의 크기만큼 반복하기 위한 while문
				Stick cur = queue.poll(); // 확인한 Stick 정보
				if (cur.x == E.x && cur.y == E.y && cur.garo == E.garo) { // cur과 E가 같다면
					while (!queue.isEmpty())
						queue.poll(); // queue를 비우기
					size = 0; // 반복문을 바로 탈출하기 위함
					A = true; // 탈출 가능함을 기록
					break;
				}

				for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우로 이동 가능한지 확인
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (cur.garo && garoCheck[nx][ny])
						continue;
					if (!cur.garo && seroCheck[nx][ny])
						continue;

					if (cur.garo)
						garoCheck[nx][ny] = true;
					else
						seroCheck[nx][ny] = true;

					boolean able = true;
					if (cur.garo) { // 현재 기둥이 가로 형태일 때, 다른 기둥과 겹치지 않는지 확인
						for (int m = 0; m < 3; m++) {

							if (grid[nx][ny + dd[m]] == '1') {
								able = false;
								m = 3;
							}
						}
					} else { // 현재 기둥이 세로 형태일 때, 다른 기둥과 겹치지 않는지 확인
						for (int m = 0; m < 3; m++) {
							if (grid[nx + dd[m]][ny] == '1') {
								able = false;
								m = 3;
							}
						}
					}
					if (able) {
						queue.add(new Stick(cur.garo, nx, ny));
					}
				}

				if(cur.x>0 && cur.x<N-1&&cur.y>0&&cur.y<N-1) {
					if(rotatable(cur.x, cur.y)) {
				
						cur.garo = !cur.garo; // 회전했을 때의 경우 확인
						if (cur.garo && garoCheck[cur.x][cur.y])
							continue;
						if (!cur.garo && seroCheck[cur.x][cur.y])
							continue;
						if (cur.garo)
							garoCheck[cur.x][cur.y] = true;
						else
							seroCheck[cur.x][cur.y] = true;

						boolean able = true;
						if (cur.garo) { // 현재 기둥이 가로 형태일 때, 다른 기둥과 겹치지 않는지 확인
							for (int m = 0; m < 3; m++) {
								if (grid[cur.x][cur.y + dd[m]] == '1') {
									able = false;
									m = 3;
								}
							}
						} else { // 현재 기둥이 세로 형태일 때, 다른 기둥과 겹치지 않는지 확인
							for (int m = 0; m < 3; m++) {
								if (grid[cur.x + dd[m]][cur.y] == '1') {
									able = false;
									m = 3;
								}
							}
						}
						if (able) {
							queue.add(new Stick(cur.garo, cur.x, cur.y));
						}
					}
				}
				
			}
		}

		if (A) // 이동 성공했다면
			bw.write(String.valueOf(count)); // 최소 동작 횟수 write
		else // 아니라면
			bw.write("0"); // 0 출력
		bw.close(); // BufferedWriter 출력 및 close
	}

	public static boolean rotatable(int x, int y) {
		for (int r = 0; r < 8; r++) {
			int nx = x + rx[r];
			int ny = y + ry[r];
			if (grid[nx][ny] == '1')
				return false;
		}

		return true;
	}
}
