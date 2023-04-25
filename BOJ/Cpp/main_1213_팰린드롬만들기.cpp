// 1213 팰린드롬 만들기

#include <iostream>
#include <string>

using namespace std;

void setAlphabet(int pos);
void checkPalindrome();

int len;
bool madable = false;

string original;
int alphabet[26] = { 0, };
char making[50];
char answer[50];

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	cin >> original;
	len = original.size();
	for (int i = 0; i < len; i++) {
		alphabet[original[i] - 'A']++;
	}

	if (len % 2 == 0) { // 길이가 짝수
		for (int i = 0; i < 26; i++) {
			if (alphabet[i] % 2 != 0) {
				cout << "I'm Sorry Hansoo";
				return 0;
			}
		}
	}
	else { // 길이가 홀수
		bool only = false;
		for (int i = 0; i < 26; i++) {
			if (alphabet[i] % 2 != 0) {
				if (only) {
					cout << "I'm Sorry Hansoo";
					return 0;
				}
				else {
					only = true;
				}
			}
		}
	}

	setAlphabet(0);

	if (!madable) {
		cout << "I'm Sorry Hansoo";
	} 
	else {
		for (int i = 0; i < len; i++) {
			cout << answer[i];
		}
	}

	return 0;
}

void setAlphabet(int pos) {
	if (madable) return;

	if (pos == len / 2) {
		if (len % 2 == 0) {
			checkPalindrome();
			return;
		}
		else {
			for (int i = 0; i < 26; i++) {
				if (alphabet[i] > 0) {
					making[pos] = (char)(i + 'A');
					checkPalindrome();
				}
			}
		}
	}

	for (int i = 0; i < 26; i++) {
		if (madable) return;
		if (alphabet[i] >= 2) {
			alphabet[i] -= 2;
			making[pos] = (char)(i + 'A');
			making[len - 1 - pos] = (char)(i + 'A');
			setAlphabet(pos + 1);
			alphabet[i] += 2;
		}
	}
}

void checkPalindrome() {
	for (int i = 0; i <= len / 2; i++) {
		if (making[i] != making[len - 1 - i]) {
			return;
		}
	}

	madable = true;
	for (int i = 0; i < len; i++) {
		answer[i] = making[i];
	}
}
