// 4375 1

#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	int n, k;
	while (cin >> n) {
		k = 1;
		int cur = 1;
		while (cur % n != 0) {
			cur *= 10;
			cur += 1;
			cur %= n;
			k++;
		}
		cout << k << "\n";
	}

	return 0;
}
