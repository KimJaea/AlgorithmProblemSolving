// 13549 숨바꼭질 3

#include <iostream>
#include <queue>

#define MAX 100001
using namespace std;

int N, K, answer = 0;
int location[MAX] = { 0, };
queue<int> subin;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> K;

	location[N] = -1;
	subin.push(N);

	if (N != 0) {
		for (int i = N * 2; i < MAX; i *= 2) {
			location[i] = -1;
			subin.push(i);
		}
	}

	if (location[K] == -1) {
		cout << 0;
		return 0;
	}

	int time = 1;
	while (!subin.empty()) {
		int s = subin.size();
		while (s-- > 0) {
			int cur = subin.front();
			subin.pop();

			if (cur == K) {
				answer = location[K];
				while (!subin.empty()) subin.pop();
				break;
			}

			if (cur > 0 && location[cur - 1] == 0) {
				int pos = cur - 1;
				location[pos] = time;
				subin.push(pos);

				if (pos != 0) {
					for (int i = pos * 2; i < MAX; i *= 2) {
						if (location[i] != 0) continue;
						location[i] = time;
						subin.push(i);
					}
				}
			}
			if (cur + 1 < MAX && location[cur + 1] == 0) {
				int pos = cur + 1;
				location[pos] = time;
				subin.push(pos);

                for (int i = pos * 2; i < MAX; i *= 2) {
					if (location[i] != 0) continue;
					location[i] = time;
					subin.push(i);
				}
			}
		}
		time++;
	}
	cout << answer;

	return 0;
}
