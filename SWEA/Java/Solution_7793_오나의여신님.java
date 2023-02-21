import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Pair {
    int x;
    int y;
 
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
 
    static boolean able;
    static int N, M, answer;
 
    static char[][] grid;
    static boolean[][] visited;
 
    static Queue<Pair> suyeon;
    static Queue<Pair> devil;
 
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 0; t < T; t++) {
            able = false;
            answer = 0;
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            grid = new char[N][M];
            visited = new boolean[N][M];
 
            suyeon = new LinkedList<>();
            devil = new LinkedList<>();
            int goalx = 0, goaly = 0;
            for (int i = 0; i < N; i++) {
                grid[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == '*') {
                        visited[i][j] = true;
                        devil.offer(new Pair(i, j));
                    } else if (grid[i][j] == 'S') {
                        grid[i][j] = '.';
                        visited[i][j] = true;
                        suyeon.offer(new Pair(i, j));
                    } else if (grid[i][j] == 'D') {
                        goalx = i;
                        goaly = j;
                    }
                }
            }
 
            while (!suyeon.isEmpty()) {
                // devil
                int s = devil.size();
                while (s-- > 0) {
                    Pair cur = devil.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            continue;
                        if (grid[nx][ny] == '.') {
                            grid[nx][ny] = '*';
                            visited[nx][ny] = true;
                            devil.offer(new Pair(nx, ny));
                        }
                    }
                }
 
                // suyeon
                s = suyeon.size();
                while (s-- > 0) {
                    Pair cur = suyeon.poll();
                    if (cur.x == goalx && cur.y == goaly) {
                        able = true;
                        while (!suyeon.isEmpty())
                            suyeon.poll();
                        break;
                    }
 
                    for (int d = 0; d < 4; d++) {
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            continue;
                        if (grid[nx][ny] == 'X' || visited[nx][ny])
                            continue;
                        // if (grid[nx][ny] == '*')
                        // continue;
 
                        visited[nx][ny] = true;
                        suyeon.offer(new Pair(nx, ny));
                    }
                }
 
                answer++;
            }
 
            bw.write("#" + String.valueOf(t + 1) + " ");
            if (able)
                bw.write(String.valueOf(answer - 1));
            else
                bw.write("GAME OVER");
            bw.newLine();
        }
        bw.close();
 
    } // main
 
}
