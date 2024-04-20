package graph.boj11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 연결 요소의 개수, www.acmicpc.net/problem/11724
public class Main {
    static int n, m;
    static int[][] graph;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }
        System.out.println(count);
    }

    // 스택을 이용한 dfs
    static void dfs(int node) {
        Stack<Integer> s = new Stack<>();
        s.push(node);
        visited[node] = true;

        while (!s.isEmpty()) {
            int now = s.pop();
            for (int i = 1; i <=n; i++) {
                if (graph[now][i] == 1 && !visited[i]) {
                    s.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    // 큐를 이용한 bfs
    static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 1; i <=n; i++) {
                if (graph[now][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    // 재귀를 이용항 dfsRecursion
    static void dfsRecursion(int node) {
        visited[node] = true;

        for (int i = 1; i <= n; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = 1;
            graph[y][x] = 1;
        }
    }
}
