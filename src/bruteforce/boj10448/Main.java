package bruteforce.boj10448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유레카 이론 https://www.acmicpc.net/problem/10448
public class Main {
    static int n;
    static int[] nums;
    static int[] triangleNumbers;
    static int last;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        setTriangleNumbers();
        input();

        for (int num : nums) {
            int result = solution(num);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution(int num) {
        for (int i = 1; i < last; i++) {
            for (int j = 1; j < last; j++) {
                for (int k = 1; k < last; k++) {
                    int sum = triangleNumbers[i] + triangleNumbers[j] + triangleNumbers[k];
                    if (sum == num) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void setTriangleNumbers() {
        triangleNumbers = new int[50];
        for (int i = 1; i < triangleNumbers.length; i++) {
            int triangleNumber = i * (i + 1) / 2;
            triangleNumbers[i] = triangleNumber;
            if (triangleNumber > 1000) break;
            last++;
        }
    }
}
