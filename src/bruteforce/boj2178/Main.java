package bruteforce.boj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로 탐색 https://www.acmicpc.net/problem/2178
public class Main {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] move = {{1,0}, {0,1}, {0,-1}, {-1,0}};

    public static void main(String[] args) throws IOException {
        input();

        visited = new boolean[n][m];
        execute(0,0);
        System.out.println(map[n-1][m-1]);
    }

    private static void execute(int x, int y) {
        // queue 방문 가능 좌표 추가
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[] {x, y});

        // queue 데이터 있는 경우 반복
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            int count = map[currentX][currentY];

            // 이동 가능 방향 체크(4방향)
            for (int i = 0; i < 4; i++) {
                int moveX = currentX + move[i][0];
                int moveY = currentY + move[i][1];


                // 이동 방향에서 미로를 벗어나는 좌표가 있는 경우 제외 처리
                if (moveX < 0 || moveY < 0 || moveX >= n || moveY >= m) {
                    continue;
                }

                // 이미 방문했으면 진행 X
                if (visited[moveX][moveY]) {
                    continue;
                }

                // 0인 경우 아래 로직 진행 X
                if (map[moveX][moveY] == 0) {
                    continue;
                }


                queue.add(new int[]{moveX, moveY});
                // 현재 정보에 +1 처리
                map[moveX][moveY] = count + 1;

                visited[moveX][moveY] = true;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] arr = st.nextToken().split("");

            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(arr[j]);
                map[i][j] = x;
            }
        }
    }
}
