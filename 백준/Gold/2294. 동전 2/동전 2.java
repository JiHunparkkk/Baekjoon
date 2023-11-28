import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] money = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = money[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - money[i]] + 1);
            }
        }

        if (dp[k] == Integer.MAX_VALUE - 1) {
            System.out.println("-1");
        } else {
            System.out.println(dp[k]);
        }
        
    }
}