package bruteforce.boj1730;

import java.util.Scanner;

// 판화(F) https://www.acmicpc.net/problem/1730
public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String command = sc.hasNext() ? sc.next() : "";

        boolean[][] passVertical = new boolean[n][n];
        boolean[][] passHorizontal = new boolean[n][n];

        int curR = 0, curC = 0;
        for (int i = 0; i < command.length(); i++) {
            char cmd = command.charAt(i);
            if (cmd == 'D') {
                if (curR == n - 1) continue;
                passVertical[curR][curC] = passVertical[curR + 1][curC] = true;
                curR++;
            }
            else if (cmd == 'U') {
                if (curR == 0) continue;
                passVertical[curR][curC] = passVertical[curR - 1][curC] = true;
                curR--;
            }
            else if (cmd == 'L') {
                if (curC == 0) continue;
                passHorizontal[curR][curC] = passHorizontal[curR][curC - 1] = true;
                curC--;
            }
            else {
                if (curC == n - 1) continue;
                passHorizontal[curR][curC] = passHorizontal[curR][curC + 1] = true;
                curC++;
            }
        }

        for (int i = 0; i < n; i++) {
            String ans = "";
            for (int j = 0; j < n; j++) {
                if (passHorizontal[i][j] && passVertical[i][j]) ans += "+";
                else if (passVertical[i][j]) ans += "|";
                else if (passHorizontal[i][j]) ans += "-";
                else ans += ".";
            }
            System.out.println(ans);
        }
    }
}
