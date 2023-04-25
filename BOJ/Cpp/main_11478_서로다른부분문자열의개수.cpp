#include <iostream>
#include <string>
#include <map>

using namespace std;

string str;
map<string, bool> subs;
bool alphabet[26] = { false, };

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	cin >> str;

	for (int i = 1; i <= str.size(); i++) { // 부분 문자열의 길이
		for (int j = 0; j <= str.size() - i; j++) { // 부분 문자열 시작 위치
			subs[str.substr(j, i)] = true;
		}
	}
	
	cout << subs.size();

	return 0;
}
