package bruteforce.boj10250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ACM 호텔 https://www.acmicpc.net/problem/10250
public class Main {
    static int testCount;
    static int[][] input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {
        for (int[] data : input) {
            int h = data[0];
            int n = data[2];

            int floor = (n-1) % h + 1;
            int roomNumber = (n-1) / h + 1;
            String result = String.format("%d%02d", floor, roomNumber);
            sb.append(result).append("\n");
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        testCount = Integer.parseInt(st.nextToken());

        input = new int[testCount][3];
        for (int i = 0; i < testCount; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
            input[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}
