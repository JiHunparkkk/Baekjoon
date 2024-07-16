import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int N = 5001;
    static int n;
    static long[] arr = new long[N];
    static long[] dp = new long[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = -1;
        }

        System.out.println(go(0));
    }

    static long go(int x) {
        if (x == n - 1) return 0;
        if (dp[x] != -1) return dp[x];

        dp[x] = Long.MAX_VALUE;
        for (int i = x + 1; i < n; i++) {
            dp[x] = Math.min(dp[x], Math.max(go(i), (i - x) * (1 + Math.abs(arr[x] - arr[i]))));
        }
        return dp[x];
    }
}