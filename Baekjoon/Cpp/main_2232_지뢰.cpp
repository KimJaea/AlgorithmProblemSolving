#include <iostream>
#include <vector>
using namespace std;

int n, tmp;
vector<int> bomb;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL), cout.tie(NULL);

	cin >> n;
	bomb.push_back(0);
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		bomb.push_back(tmp);
	}
	bomb.push_back(0);

	bool inc = true;
	int pre_i = 0;
	for (int i = 1; i <= n + 1; i++) {
		if (inc) {
			if (bomb[i] > bomb[pre_i]) {
				pre_i = i;
			}
			else if (bomb[i] == bomb[pre_i]) {
				cout << pre_i << "\n";
				pre_i = i;
			}
			else {
				cout << pre_i << "\n";
				pre_i = i;
				inc = false;
			}
		}
		else {
			if (bomb[i] < bomb[pre_i]) {
				pre_i = i;
			}
			else if (bomb[i] == bomb[pre_i]) {
				pre_i = i;
				inc = true;
			}
			else {
				pre_i = i;
				inc = true;
			}
		}
	}

	return 0;
}
