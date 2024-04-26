package bfs.boj12851;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질2, https://www.acmicpc.net/problem/12851
public class Main {
    static int n, k;
    static int[] visited;
    static int[] count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        visited = new int[100001];
        count = new int[100001];

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 1;
        count[n] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            int[] nextArr = {now + 1, now - 1, 2 * now};
            for (int i = 0; i < 3; i++) {
                int next = nextArr[i];
                if (next < 0 || next > 100000) continue;
                if (visited[next] == 0) {
                    visited[next] = visited[now] + 1;
                    count[next] = count[now];
                    q.add(next);
                } else if (visited[next] == visited[now] + 1) {
                    count[next] += count[now];
                }
            }
        }
        System.out.println(visited[k] - 1);
        System.out.println(count[k]);
    }
}
