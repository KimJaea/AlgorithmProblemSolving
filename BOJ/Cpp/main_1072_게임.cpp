// 1072 게임

#include <iostream>
#include <string>
#include <map>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	long long int x, y;
	cin >> x >> y;
	
	long long int cur = (y * 100) / x;
	if (cur >= 99) {
		cout << -1;
	}
	else {
		cur++;
		// 이분 탐색, cur이 되는 값 중 최솟값 찾기
		long long int left = 1, right = 1000000000, mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			long long int tmp = ((y + mid) * 100) / (x + mid);
			if (tmp < cur) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		if (((y + mid) * 100) / (x + mid) < cur) cout << mid + 1;
		else cout << mid;
	}

	return 0;
}
