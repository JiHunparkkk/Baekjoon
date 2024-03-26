import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n + 1];
        int[] cost = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            total += cost[i];
        }

        int[][] dp = new int[n + 1][total + 1];    //i번째 앱, j의  비용, dp[i][j] = 필요한 메모리 바이트

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= total; j++) {
                if (j - cost[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
            }
        }

        for (int i = 0; i <= total; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }
}