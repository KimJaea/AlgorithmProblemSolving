import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 위한 BufferedWriter 변수 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 변수 선언
	static StringTokenizer st = null; // 입력 값을 공백 기준으로 분리하기 위한 변수 선언

	static int S, A; // 스카프의 장수를 저장하는 변수 S, 정답을 저장하는 변수 A
	static boolean[][] base = new boolean[100][100]; // 흰색 천의 영역을 나타내는 배열 base, true는 스카프가 위치한 곳을 의미

	static int dx[] = { 0, 1, 0, -1 }; // 4방 탐색을 위한 배열
	static int dy[] = { 1, 0, -1, 0 }; // 4방 탐색을 위한 배열

	public static void main(String[] args) throws NumberFormatException, IOException { // main method
		S = Integer.parseInt(br.readLine()); // 스카프의 장수 입력 받기

		for (int s = 0; s < S; s++) { // 스카프의 장수만큼 입력을 받기 위한 반복문, 스카프가 전체에서 얼마나 차지하는지가 중요하므로 임의로 배열을 회전, 반전시킴
			st = new StringTokenizer(br.readLine()); // 흰색 천과의 거리를 나타내는 두 변수 입력 받기
			int a = Integer.parseInt(st.nextToken()); // StringTokenizer를 이용해 왼쪽 변과의 거리 받기, 현재 배열에서는 위쪽 변과의 거리 받기
			int b = Integer.parseInt(st.nextToken()); // StringTokenizer를 이용해 아래쪽 변과의 거리 받기, 현재 배열에서는 왼쪽 변과의 거리 받기

			for (int i = 0; i < 10; i++) { // 반복문을 통해 스카프가 놓인 행 찾기
				for (int j = 0; j < 10; j++) { // 반복문을 통해 스카프가 놓인 열 찾기
					base[a + i][b + j] = true; // 스카프가 놓임을 표시하기 위해 true
				}
			}
		}

		A = 0; // 스카프의 둘레를 구하기 위한 변수 0으로 초기화
		for (int i = 0; i < 100; i++) { // 반복문을 통해 모든 영역을 확인하기
			for (int j = 0; j < 100; j++) { // 반복문을 통해 모든 영역을 확인하기

				if (base[i][j]) { // 현재 위치에 스카프가 있다면
					for (int d = 0; d < 4; d++) { // 4방에 스카프가 있는지 확인
						int nx = i + dx[d]; // 확인할 자리의 위치
						int ny = j + dy[d]; // 확인할 자리의 위치

						if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100) { // 해당 위치가 흰 천 위라면
							if (!base[nx][ny]) { // 스카프가 없을 때
								A++; // 둘레를 1 추가
							}
						} else { // 흰 천 밖이라면
							A++; // 스카프가 없으므로 그만큼 둘레를 1 추가
						}

					}
				}

			}
		}

		bw.write(String.valueOf(A)); // 둘레를 출력하기 위해 write
		bw.close(); // 둘레를 출력
	}
}
