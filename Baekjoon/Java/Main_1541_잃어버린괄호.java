import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 1541 잃어버린 괄호

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		char[] cont = str.toCharArray();
		ArrayList<Integer> nums = new ArrayList<>();
		ArrayList<Boolean> plus = new ArrayList<>(); // true: +, false: -

		int index = 0;
		while (index < str.length()) {
			if (cont[index] == '+' || cont[index] == '-') {
				if (cont[index] == '+') {
					plus.add(true);
				} else {
					plus.add(false);
				}
				index++;
			} else {
				int tmp = 0;
				while (index < str.length() && cont[index] != '+' && cont[index] != '-') {
					tmp *= 10;
					tmp += (cont[index] - '0');
					index++;
				}
				nums.add(tmp);
			}
		}

		int answer = 0, total = 0;
		for (int i = nums.size() - 1; i > 0; i--) {
			total += nums.get(i);
			if (!plus.get(i - 1)) {
				answer -= total;
				total = 0;
			}
		}
		if (total > 0)
			answer += total;
		answer += nums.get(0);
		System.out.print(answer);
	}
}