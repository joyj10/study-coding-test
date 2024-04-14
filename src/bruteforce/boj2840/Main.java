package bruteforce.boj2840;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 행운의 바퀴 https://www.acmicpc.net/problem/2840
public class Main {

    static int wheelSize;
    static int count;
    static String[][] arr;

    public static void main(String[] args) throws IOException {
        input();

        String[] result = solution();

        System.out.println(String.join("", result));
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        wheelSize = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        arr = new String[count][2];

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();
        }
    }

    private static String[] solution() {
        String[] result = new String[wheelSize];
        Arrays.fill(result, "?");

        List<String> already = new ArrayList<>();
        int point = 0;
        for (int i = count - 1; i >= 0; i--) {
            String text = arr[i][1];

            String pointText = result[point];
            if (!"?".equals(pointText) && !pointText.equals(text)) {
                return new String[]{"!"};
            }

            if (!pointText.equals(text) && already.contains(text)) {
                return new String[]{"!"};
            }

            result[point] = text;

            int n = Integer.parseInt(arr[i][0]);
            point = Math.abs(point + n) % wheelSize;
            already.add(text);
        }
        return result;
    }
}
