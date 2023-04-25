import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Queue<Integer> cards = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			cards.offer(i);

		while (cards.size() > 1) {
			cards.remove();
			cards.offer(cards.poll());
		}

		System.out.println(cards.poll());
	}
}
