import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[3][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[3][n + 1];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == j) {
                    dp[i][1] = arr[j][1];
                } else {
                    dp[j][1] = 1001;
                }
            }
            for (int k = 2; k <= n; k++) {
                dp[0][k] = Math.min(dp[1][k - 1], dp[2][k - 1]) + arr[0][k];
                dp[1][k] = Math.min(dp[0][k - 1], dp[2][k - 1]) + arr[1][k];
                dp[2][k] = Math.min(dp[0][k - 1], dp[1][k - 1]) + arr[2][k];
            }

            for (int j = 0; j < 3; j++) {
                if(i == j) continue;
                answer = Math.min(answer, dp[j][n]);
            }
        }
        System.out.println(answer);
    }
}