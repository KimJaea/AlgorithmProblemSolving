import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 1463 1로 만들기

class Cal {
	int num;
	int cnt;

	public Cal(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		Queue<Cal> queue = new LinkedList<Cal>();
		queue.add(new Cal(N, 0));

		int answer = 0;
		while (!queue.isEmpty()) {
			Cal c = queue.poll();

			if (c.num == 1) {
				answer = c.cnt;
				break;
			}
			if (c.num % 3 == 0)
				queue.add(new Cal(c.num / 3, c.cnt + 1));
			if (c.num % 2 == 0)
				queue.add(new Cal(c.num / 2, c.cnt + 1));
			queue.add(new Cal(c.num - 1, c.cnt + 1));
		}

		System.out.print(answer);
	}
}
