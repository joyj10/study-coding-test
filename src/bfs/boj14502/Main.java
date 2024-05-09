package bfs.boj14502;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소, https://www.acmicpc.net/problem/14502
public class Main {
    static int n;
    static int m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static int[][] copyMap;

    static Queue<Virus> queue = new LinkedList<>();
    static int maxSafetyRoom = Integer.MIN_VALUE;

    static class Virus {
        int x;
        int y;
        public Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(0);

        System.out.println(maxSafetyRoom);
    }


    public  static void dfs(int wallCnt) {
        if(wallCnt == 3) {
            bfs();
            return;
        }

        for(int i = 0; i< n; i++) {
            for(int j = 0; j< m; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wallCnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(){
        for(int i = 0; i< n; i++) {
            for(int j = 0; j< m; j++) {
                if(map[i][j] == 2) {
                    queue.offer(new Virus(i,j));
                }
            }
        }

        copyMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            copyMap[i] = map[i].clone();
        }

        while (!queue.isEmpty()){
            Virus v = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                if(0 <= nx && nx < n && 0<= ny && ny < m) {
                    if(copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2;
                        queue.add(new Virus(nx, ny));
                    }
                }
            }

        }

        funcSafeZone(copyMap);
    }

    private static void funcSafeZone(int[][] copyMap) {
        int safeZone =0;
        for(int i = 0; i< n; i++) {
            for(int j = 0; j< m; j++) {
                if(copyMap[i][j] == 0) {
                    safeZone++;
                }
            }
        }

        maxSafetyRoom = Math.max(safeZone, maxSafetyRoom);

    }
}
