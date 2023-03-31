#include <iostream>
#include <string>
#include <map>
#include <vector>
#include <utility>
#include <algorithm>

// 7785 회사에 있는 사람

using namespace std;

map<string, bool> person;
vector<string> answer;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;
	string p, m; // person, movement
	for (int i = 0; i < n; i++) {
		cin >> p >> m;
		if (m == "enter") person[p] = true;
		else person[p] = false;
	}

	map<string, bool>::iterator iter;
	for (iter = person.begin(); iter != person.end(); iter++) {
		if (iter->second) answer.push_back(iter->first);
	}
	sort(answer.begin(), answer.end());
	for (int i = answer.size() - 1; i >= 0; i--) {
		cout << answer[i] << "\n";
	}

	return 0;
}
