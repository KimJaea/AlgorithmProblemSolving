import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node {
	String val;
	Node next;

	public Node(String val) {
		this.val = val;
		this.next = null;
	}

	public Node(String val, Node next) {
		this.val = val;
		this.next = next;
	}

}

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int n, m;
	static Node head, pre, cur;

	public static void main(String[] args) throws NumberFormatException, IOException {

		for (int t = 0; t < 10; t++) {
			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			head = new Node(st.nextToken(), null);

			cur = head;
			for (int i = 1; i < n; i++) {
				cur.next = new Node(st.nextToken(), null);
				cur = cur.next;
			}

			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int insertCount = 0;
			while (insertCount++ < m) {
				st.nextToken();

				int startNodeNum = Integer.parseInt(st.nextToken());
				int nodeCount = Integer.parseInt(st.nextToken());

				if (startNodeNum == 0) {
					head = new Node(st.nextToken(), head);
					pre = head;
					for (int j = 1; j < nodeCount; j++) {
						cur = new Node(st.nextToken(), pre.next);
						pre.next = cur;
						pre = pre.next;
					}
				} else {
					pre = head;
					for (int j = 1; j < startNodeNum; j++) {
						pre = pre.next;
					}

					for (int j = 0; j < nodeCount; j++) {
						cur = new Node(st.nextToken(), pre.next);
						pre.next = cur;
						pre = pre.next;
					}

				}
			}

			bw.write('#');
			bw.write(String.valueOf(t + 1));

			Node cur = head;
			for (int i = 0; i < 10; i++) {
				if (cur.next == null)
					break;
				bw.write(' ');
				bw.write(cur.val);
				cur = cur.next;
			}
			bw.newLine();
		}

		bw.close();

	}

}
