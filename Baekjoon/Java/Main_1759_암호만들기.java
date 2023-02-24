import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1759 암호 만들기

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int L, C;
	static boolean[] alphabet = new boolean[26];

	static int[] answer;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[st.nextToken().charAt(0) - 'a'] = true;
		}

		answer = new int[L];
		selectAlphabet(0, 0, 0);
		bw.close();
	}

	public static void selectAlphabet(int pos, int start, int vowel) throws IOException {
		if (pos == L) {
			if (vowel < 1 || pos - vowel < 2)
				return;
			for (int i = 0; i < L; i++) {
				bw.write((char) ('a' + answer[i]));
			}
			bw.newLine();
			return;
		}

		for (int i = start; i < 26; i++) {
			if (alphabet[i]) {
				alphabet[i] = false;
				answer[pos] = i;
				if (i == 0 || i == 4 || i == 8 || i == 14 || i == 20)
					selectAlphabet(pos + 1, i + 1, vowel + 1);
				else
					selectAlphabet(pos + 1, i + 1, vowel);
				alphabet[i] = true;
			}
		}
	}

}