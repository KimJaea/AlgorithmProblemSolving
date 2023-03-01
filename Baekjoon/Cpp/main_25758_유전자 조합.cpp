#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0), cout.tie(0);

    int n;
    string str;
    int gene[26][26];

    // Initialize
    for (int i = 0; i < 26; i++) {
        for (int j = 0; j < 26; j++) {
            gene[i][j] = 0;
        }
    }

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> str;
        gene[str[0] - 'A'][str[1] - 'A']++;
    }
    
    int total = 0;
    bool answer[26] = { false, };

    for (int i = 0; i < 26; i++) {
        for (int j = 0; j < 26; j++) {
            if (gene[i][j] == 0) continue;
            
            for (int k = 0; k < 26; k++) {
                for (int l = 0; l < 26; l++) {
                    if (gene[k][l] == 0) continue;
                    
                    if (i == k && j == l) {
                        if (gene[i][j] == 1) break;
                        if (i < j) { // j
                            if (!answer[j]) {
                                answer[j] = true;
                                total++;
                            }
                        }
                        else { // i
                            if (!answer[i]) {
                                answer[i] = true;
                                total++;
                            }
                        }
                    }
                    else {
                        if (i < l) { // l
                            if (!answer[l]) {
                                answer[l] = true;
                                total++;
                            }
                        }
                        else { // i
                            if (!answer[i]) {
                                answer[i] = true;
                                total++;
                            }
                        }
                        if (j < k) { // k
                            if (!answer[k]) {
                                answer[k] = true;
                                total++;
                            }
                        }
                        else { // j
                            if (!answer[j]) {
                                answer[j] = true;
                                total++;
                            }
                        }
                    }
                    if (total == 26) break;
                }
                if (total == 26) break;
            }
            if (total == 26) break;
        }
        if (total == 26) break;
    }

    cout << total << "\n";
    for (int i = 0; i < 26; i++) {
        if (answer[i] > 0) cout << (char)(i + 'A') << " ";
    }

    return 0;
}
