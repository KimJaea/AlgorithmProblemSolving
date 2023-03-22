// 17086 아기 상어 2

#include <iostream>
#include <queue>
#include <utility>
#include <cmath>
#define MAX 2500

using namespace std;

int N, M;
int dis[50][50];

queue<pair<int, int>> moving;
int dx[8] = { 0, 1, 1, 1, 0, -1, -1, -1 };
int dy[8] = { 1, 1, 0, -1, -1, -1, 0, 1 };

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> dis[i][j];

			if (dis[i][j] == 1) {
				moving.push({ i, j });
				dis[i][j] = 0;
			}
			else {
				dis[i][j] = MAX;
			}
		}
	}

	int cnt = 1;
	while (!moving.empty()) {
		int ms = moving.size();
		while (ms-- > 0) {
			int x = moving.front().first;
			int y = moving.front().second;
			moving.pop();

			for (int d = 0; d < 8; d++) {
				int cx = x + dx[d];
				int cy = y + dy[d];
				if (cx < 0 || cx >= N || cy < 0 || cy >= M) continue;

				if (dis[cx][cy] > cnt) {
					dis[cx][cy] = cnt;
					moving.push({ cx, cy });
				}
			}

		}
		cnt++;
	}

	int answer = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			answer = max(answer, dis[i][j]);
		}
	}
	cout << answer;

	return 0;
}
