import java.io.IOException;
import java.util.Scanner;

public class Main {

	static int N, r, c;
	static int[][] grid;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		int k = 0, x = 0, y = 0;
		while (N > 1) {
			int half = (int) Math.pow(2, N - 1);
			int val = (int) Math.pow(4, N - 1);
			if (r < x + half) {
				if (c < y + half) { // 좌상
				} else { // 우상
					y += half;
					k += val;
				}
			} else {
				if (c < y + half) { // 좌하
					x += half;
					k += val * 2;
				} else { // 우하
					x += half;
					y += half;
					k += val * 3;
				}

			}
			N--;
		}
		if (x == r) {
			if (y == c) { // 좌상
			} else { // 우상
				k += 1;
			}
		} else {
			if (y == c) { // 좌하
				k += 2;
			} else { // 우하
				k += 3;
			}
		}

		System.out.print(k);
	}

}
