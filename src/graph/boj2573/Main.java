package graph.boj2573;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 빙산, https://www.acmicpc.net/problem/2573
public class Main {
    static int n, m;
    static int[][] earth;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List<Ice> iceList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        earth = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                earth[i][j] = sc.nextInt();
                if (earth[i][j] > 0) {
                    iceList.add(new Ice(i, j, earth[i][j]));
                }
                visited[i][j] = true;
            }
        }

        // 시간이 흐를때 마다 빙산 녹이기
        for (int year = 1; !iceList.isEmpty(); year++) {
            for (Ice ice : iceList) {
                for (int i = 0; i < 4; i++) {
                    int nr = ice.row + dr[i];
                    int nc = ice.col + dc[i];
                    if (earth[nr][nc] == 0) {
                        ice.height--;
                    }
                }
            }

            // 녹은 빙산들의 높이를 한번에 갱신
            for (int i = 0; i < iceList.size(); i++) {
                Ice ice = iceList.get(i);
                if (ice.height <= 0) {
                    earth[ice.row][ice.col] = 0;
                    iceList.set(i, iceList.get(iceList.size() - 1));
                    iceList.remove(iceList.size() - 1);
                    i--;
                }
                // 빙산이 남아있다면 연결되어 있는지 탐색하도록 세팅
                else {
                    visited[ice.row][ice.col] = false;
                }
            }

            if (!iceList.isEmpty() &&
                    dfs(iceList.get(0).row, iceList.get(0).col) != iceList.size()) {
                System.out.println(year);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

    static int dfs(int r, int c) {
        visited[r][c] = true;
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (visited[nr][nc]) continue;
            cnt += dfs(nr, nc);
        }
        return cnt;
    }
}

class Ice {
    int row;
    int col;
    int height;

    public Ice(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }
}
