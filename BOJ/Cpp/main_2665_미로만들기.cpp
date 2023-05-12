#include <iostream>
#include <string>
#include <queue>
#include <utility>
using namespace std;

int n;
bool room[50][50];
int answer[50][50];

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

// priority_queue<pair<int, pair<int, int>>> maze;
queue<pair<int, pair<int, int>>> maze;

// 희게 만든 방이 적으면서 출구에 가까운 것의 우선순위가 높도록 정렬
struct compare {
	bool operator() (pair<int, pair<int, int>> a, pair<int, pair<int, int>> b) {
		if (a.first == b.first) {
			if (a.second.first == b.second.first)
				return a.second.second > b.second.second;
			else
				return a.second.first > b.second.first;
		}
		else {
			return a.first < b.first;
		}
	}
};

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	cin >> n;
	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < n; j++) {
			if (str[j] == '1')room[i][j] = true;
			answer[i][j] = n * 2;
		}
	}

	answer[0][0] = 0;
	maze.push({ 0, {0, 0} });

	while (!maze.empty()) {
		/*
		int c = maze.top().first;
		int x = maze.top().second.first;
		int y = maze.top().second.second;
		maze.pop();
		*/
		int c = maze.front().first;
		int x = maze.front().second.first;
		int y = maze.front().second.second;
		maze.pop();

		if (answer[x][y] < c) continue;
		if (x == n - 1 && y == n - 1) continue;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (room[nx][ny]) { // 다음 방은 흰 방
				if (c < answer[nx][ny]) {
					answer[nx][ny] = c;
					maze.push({ c, {nx, ny} });
				}
			}
			else { // 다음 방은 검은 방
				if (c + 1 < answer[nx][ny]) {
					answer[nx][ny] = c + 1;
					maze.push({ c + 1, {nx, ny} });
				}
			}
		}
	}
	cout << answer[n - 1][n - 1];

	return 0;
}
