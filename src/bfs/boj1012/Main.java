package bfs.boj1012;

import java.util.*;
import java.io.*;

// 유기농 배추, https://www.acmicpc.net/problem/1012
public class Main {
    static Queue<Node> que = new LinkedList<>();
    static int dirX[] = {0, 0, -1, 1};
    static int dirY[] = {-1, 1, 0, 0};
    static int map[][];
    static boolean visit[][];

    static int now_x, now_y;
    static int N,M,K;
    static int count;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visit = new boolean[N][M];

            for(int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }

            count = 0;
            for(int j=0; j<N; j++) {
                for(int k=0; k<M; k++) {

                    if(visit[j][k] == false && map[j][k] == 1) {
                        count++;
                        BFS(k, j);
                    }
                }
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb);
    }

    static void BFS(int x, int y) {
        que.offer(new Node(x, y));
        visit[y][x] = true;

        while( !que.isEmpty() ) {
            Node node = que.poll();

            for(int i=0; i<4; i++) {
                now_x = node.x + dirX[i];
                now_y = node.y + dirY[i];

                if(Range_check() && visit[now_y][now_x] == false && map[now_y][now_x] == 1) {
                    que.offer(new Node(now_x, now_y));
                    visit[now_y][now_x] = true;
                }

            }
        }
    }

    public static boolean Range_check() {
        return (now_x >= 0 && now_x < M && now_y >= 0 && now_y < N);
    }
}
