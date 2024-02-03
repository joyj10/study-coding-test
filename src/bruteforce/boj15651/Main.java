package bruteforce.boj15651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15651
public class Main {
    static int n;
    static int m;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        selected = new int[m + 1];
        execute(1);
        System.out.println(sb.toString());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    static void execute(int k) {
        if (k == m + 1) {
            for (int i = 1; i <= m; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int i = 1; i <= n; i++) {
                selected[k] = i;
                execute(k + 1);
            }
        }
    }
}
