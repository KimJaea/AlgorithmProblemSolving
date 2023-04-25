#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <map>

// 1148 단어 만들기

using namespace std;

vector<string> dictionary;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	string str;

	cin >> str;
	while (str != "-") {
		dictionary.push_back(str);
		cin >> str;
	}

	cin >> str;
	while (str != "#") {
		int puzzle[26] = { 0, };
		for (int i = 0; i < str.size(); i++) puzzle[str[i] - 'A']++;

		int max_cnt = 0, min_cnt = 200000;
		int completed[26]; // 가운데에 char 둘 경우, 완성한 단어 수 저장
		for (int i = 0; i < 26; i++) completed[i] = -1;

		for (int i = 0; i < str.size(); i++) {
			if (completed[str[i] - 'A'] != -1) continue;
			char center = str[i]; // 가운데에 둘 알파벳
			int madeDic = 0;

			for (int j = 0; j < dictionary.size(); j++) {
				string word = dictionary[j];

				if (word.find(center) != string::npos) {
					int tmp[26];
					bool able = true;
					for (int j = 0; j < 26; j++) {
						tmp[j] = puzzle[j];
					}

					for (int k = 0; k < word.size(); k++) {
						int pos = (int)(word[k] - 'A');
						tmp[pos]--;
						if (tmp[pos] < 0) {
							able = false;
							k = 10;
						}
					}
					if (able) {
						madeDic++;
					}
				}
			}

			completed[center - 'A'] = madeDic;
			if (madeDic > max_cnt) {
				max_cnt = madeDic;
			}
			if (madeDic < min_cnt) {
				min_cnt = madeDic;
			}
		}

		for (int i = 0; i < 26; i++) {
			if (completed[i] == min_cnt) cout << (char)(i + 'A');
		}
		cout << " " << min_cnt << " ";
		for (int i = 0; i < 26; i++) {
			if (completed[i] == max_cnt) cout << (char)(i + 'A');
		}
		cout << " " << max_cnt << "\n";

		cin >> str;
	}

	return 0;
}
