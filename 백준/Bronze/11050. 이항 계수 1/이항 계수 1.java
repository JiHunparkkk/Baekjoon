import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][k + 1];

        System.out.println(solution(n, k));
    }

    private static int solution(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (dp[n][k] == 0) {
            dp[n][k] = solution(n - 1, k - 1) + solution(n - 1, k);
        }
        return dp[n][k];
    }
}