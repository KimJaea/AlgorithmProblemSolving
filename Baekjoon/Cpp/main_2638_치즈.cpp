// 2638 치즈

#include <iostream>
#include <queue>
#include <utility>
#define SIZE 100
using namespace std;

int N, M;
int aired[SIZE][SIZE];
bool grid[SIZE][SIZE];

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

void cheese() {
	bool check[SIZE][SIZE];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (grid[i][j]) aired[i][j] = 2;
			else aired[i][j] = 0;
			check[i][j] = false;
		}
	}

	// bfs로 공기 방문
	queue<pair<int, int>> air;

	check[0][0] = true;
	air.push({ 0,0 });
	while (!air.empty()) {
		int x = air.front().first;
		int y = air.front().second;
		air.pop();

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if (check[nx][ny]) continue;

			if (grid[nx][ny]) {
				aired[nx][ny]--;
			} 
			else {
				check[nx][ny] = true;
				air.push({ nx,ny });
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (aired[i][j] > 0) grid[i][j] = true;
			else grid[i][j] = false;
		}
	}
}

bool notyet() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (grid[i][j]) return true;
		}
	}
	return false;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> grid[i][j];
		}
	}

	int time = 0;
	while (notyet()) {
		cheese();
		time++;
	}
	cout << time;

	return 0;
}
