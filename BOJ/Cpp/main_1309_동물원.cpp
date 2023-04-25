// 1309 동물원

#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int n;
	cin >> n;

	int bef[3] = { 1, 1, 1 };
	int cur[3] = { 1, 1, 1 };
	for (int i = 2; i <= n; i++) {
		cur[0] = bef[0] + bef[1] + bef[2];
		cur[1] = bef[0] + bef[2];
		cur[2] = bef[0] + bef[1];
		for (int j = 0; j < 3; j++) {
			cur[j] %= 9901;
			bef[j] = cur[j];
		}
	}

	cout << (cur[0] + cur[1] + cur[2]) % 9901;

	/*
	int dp[100001][3];
	dp[1][0] = 1; // 아무것도 배치하지 않은 경우
	dp[1][1] = 1; // 왼쪽에만 배치하는 경우
	dp[1][2] = 1; // 오른쪽에만 배치하는 경우
	for (int i = 2; i <= n; i++) {
		dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
		dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
		dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
	}

	cout << (dp[n][0] + dp[n][1] + dp[n][2]);
	*/

	return 0;
}
