import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int MAX_INT = 400_000;

    private static int[] arr;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[st.countTokens()];
        for(int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //i까지 고려했을 때, 왼발 또는 오른발의 위치에 따라 힘의 최소값 찾기
        dp = new int[arr.length + 1][5][5];
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                for (int k = 0; k < dp[i][j].length; k++) {
//                    dp[i][j][k] = MAX_INT;
//                }
//            }
//        }
//
//        dp[0][0][0] = 0;

        int answer = solution(1, 0, 0);
        System.out.println(answer);
    }

    private static int solution(int nowIdx, int left, int right) {
        if(nowIdx == arr.length) return 0;
        if(dp[nowIdx][left][right] != 0) {
            return dp[nowIdx][left][right];
        }

        int next = arr[nowIdx];
        dp[nowIdx][left][right] = Math.min(solution(nowIdx + 1, next, right) + move(next, left),
                solution(nowIdx + 1, left, next) + move(right, next));

        return dp[nowIdx][left][right];
    }

    private static int move( int prevK, int nextK) {
        int power;
        if(prevK == nextK) {
            power = 1;
        } else if(prevK == 0 || nextK == 0) {
            power = 2;
        } else if(Math.abs(prevK - nextK) % 2 == 1) {
            power = 3;
        } else {
            power = 4;
        }
        return power;
    }
}