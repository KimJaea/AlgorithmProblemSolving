import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N, L;
	static int[] fruits;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int answer = -1;
		boolean driable = false;
		for (int i = 0; i <= n / 5; i++) {
			int m = n - (5 * i);
			if (m % 3 == 0) {
				int cur = i + (m / 3);
				if (driable) {
					if (answer > cur)
						answer = cur;
				} else {
					driable = true;
					answer = cur;
				}
			}
		}
		if (driable)
			System.out.print(answer);
		else
			System.out.println(-1);
	}
}
