package bruteforce.boj14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14888
public class Main {
    static int n;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] nums;
    static int[] operators;
    static int[] orders;

    public static void main(String[] args) throws IOException {
        input();

        execute(1);
        System.out.println(max);
        System.out.println(min);
    }

    static void execute(int k) {
        if (k == n) {
            int value = calculate();
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else {
            for (int i = 1; i < 5; i++) {
                if (operators[i] >= 1) {
                   operators[i]--;
                   orders[k] = i;
                   execute(k+1);
                   operators[i]++;
                   orders[k] = 0;
                }
            }
        }
    }

    static int calculate() {
        int value = nums[1];
        for (int i = 1; i < n; i++) {
            switch (orders[i]) {
                case 1:
                    value += nums[i+1];
                    break;
                case 2:
                    value -= nums[i+1];
                    break;
                case 3:
                    value *= nums[i+1];
                    break;
                case 4:
                    value /= nums[i+1];
                    break;
                default:
            }
        }
        return value;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        nums = new int[n+1];
        operators = new int[5];
        orders = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < operators.length; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
    }
}
