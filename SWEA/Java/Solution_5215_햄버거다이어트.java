import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
 
//5215 햄버거 다이어트
 
public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
 
    static int N, L, bestTaste;
    static int[] taste;
    static int[] calory;
    // static boolean[] check;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
 
            taste = new int[N];
            calory = new int[N];
            // check = new boolean[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                taste[i] = Integer.parseInt(st.nextToken());
                calory[i] = Integer.parseInt(st.nextToken());
            }
 
            bestTaste = 0;
            makeBurger(0, 0, 0);
            System.out.printf("#%d %d\n", t + 1, bestTaste);
        }
 
    }
 
    public static void makeBurger(int p, int t, int c) {
        if (c > L)
            return;
         
        if (p >= N) {
            if (t > bestTaste)
                bestTaste = t;
            return;
        }
         
        makeBurger(p + 1, t + taste[p], c + calory[p]);
        makeBurger(p + 1, t, c);
    }
 
}