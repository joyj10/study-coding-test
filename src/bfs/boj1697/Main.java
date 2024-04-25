package bfs.boj1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질, https://www.acmicpc.net/problem/1697
public class Main {
    static int n, k;
    static int[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        visited = new int[100001];


        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == k) break;
            int[] next = {now -1, now + 1, now * 2};
            for (int i = 0; i < 3; i++) {
                int nextNumber = next[i];
                if (!isRange(nextNumber)) continue;
                if (visited[nextNumber] == 0) {
                    visited[nextNumber] = visited[now] + 1;
                    q.add(nextNumber);
                }
            }
        }
        System.out.println(visited[k]-1);
    }

    static boolean isRange(int x) {
        return x >= 0 && x <= 100000;
    }
}
