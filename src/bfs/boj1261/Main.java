package bfs.boj1261;

import java.util.*;
import java.io.*;

// 알고스팟 https://www.acmicpc.net/problem/1261
public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        isVisited = new boolean[n][m];
        for(int i = 0; i< n; i++) {
            String s = br.readLine();
            for(int j = 0; j< m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int ans = bfs(0,0);

        System.out.println(ans);
    }

    static int bfs(int x, int y) {
        PriorityQueue<Pos> que = new PriorityQueue<>(); // 우선순위 큐를 사용하여 갈 수 있는 곳 먼저 모두 탐색하고 벽으로 막혀 있으면 벽을 부수고 탐색
        int minDes = Integer.MAX_VALUE;

        que.add(new Pos(x, y, 0));
        isVisited[x][y] = true;

        while(!que.isEmpty()) {
            Pos p = que.poll();
            int curX = p.x;
            int curY = p.y;

            if(curX == n -1 && curY == m -1) { // 탈출 조건
                minDes = Math.min(minDes, p.desCnt);
                return minDes;
            }

            for(int t=0; t<4; t++) {
                int nx = curX + dx[t];
                int ny = curY + dy[t];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if(!isVisited[nx][ny]) {
                    if(map[nx][ny] == 0) { // 탐색한 좌표가 통과할 수 있는 길이면 큐에 추가
                        que.add(new Pos(nx, ny, p.desCnt));
                    }
                    if(map[nx][ny] == 1) { // 탐색한 좌표가 벽이면 벽 부수는 횟수를 1 추가하여 큐에 추가
                        que.add(new Pos(nx, ny, p.desCnt + 1));
                    }
                    isVisited[nx][ny] = true;
                }
            }

        }
        return -1;
    }

    static class Pos implements Comparable<Pos>{ // 우선순위 큐를 사용할 것이기 때문에 Comparable 구현
        int x;
        int y;
        int desCnt;

        Pos(int x, int y, int desCnt){
            this.x = x;
            this.y = y;
            this.desCnt = desCnt;
        }

        @Override
        public int compareTo(Pos o) { // 인자로 전달한 o가 작다면 양의 정수를, 크다면 음의 정수를, 같다면 0 반환
            return this.desCnt - o.desCnt; // 벽 부순 횟수가 더 작은 경우를 우선으로 탐색
        }
    }

}
