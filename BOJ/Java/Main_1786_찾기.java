import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// 1786 찾기

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static ArrayList<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		String T = br.readLine();
		String P = br.readLine();

		if (T.length() >= P.length()) {
			// search1(T, P);
			search2(T, P);
		}

		if (answer.isEmpty())
			bw.write("0");
		else {
			bw.write(String.valueOf(answer.size()) + "\n");
			for (int i = 0; i < answer.size(); i++) {
				bw.write(String.valueOf(answer.get(i)) + " ");
			}
		}

		bw.close();
	}

	// 라빈 카프 알고리즘
	public static void search1(String T, String P) throws IOException {
		int d = 29; // random prime number
		int m = (int) 1e9 + 7; // number to mod

		long head = 1;
		long tt = 0, pt = 0;
		int s = 0, e = P.length();
		for (int i = 0; i < P.length(); i++) {
			tt = (tt * d + T.charAt(i)) % m;
			pt = (pt * d + P.charAt(i)) % m;

			if (i != 0)
				head = (head * d) % m;
		}

		while (e <= T.length()) {
			if (tt == pt) {
				answer.add(s + 1);
			}

			if (e >= T.length())
				break;

			tt = (d * (tt - T.charAt(s) * head) + T.charAt(e)) % m;
			if (tt < 0)
				tt += m;
			e++;
			s++;
		}

	}

	// KMP 알고리즘
	public static void search2(String T, String P) {
		int tl = T.length();
		int pl = P.length();

		int[] pi = new int[pl];

		int j = 0;
		for (int i = 1; i < pl; i++) {
			while (j > 0 && P.charAt(j) != P.charAt(i)) {
				j = pi[j - 1];
			}
			if (P.charAt(j) == P.charAt(i)) {
				pi[i] = ++j;
			}
		}

		j = 0;
		for (int i = 0; i < tl; i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j)) {
				j = pi[j - 1];
			}
			if (T.charAt(i) == P.charAt(j)) {
				if (j + 1 == pl) {
					// answer.add(i - j + 1);
					answer.add(i - pl + 2);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}
}
