import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        dp = new int[101][101][101];
        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (isEnd(a, b, c)) {
                break;
            }

            int result = solution(a + 50, b + 50, c + 50);
            dp[a + 50][b + 50][c + 50] = result;
            sb.append("w(" + a + ", " + b + ", " + c + ") = ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(int a, int b, int c) {
        if(dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        if(a <= 50 || b <= 50 || c <= 50) {
            return dp[a][b][c] = 1;
        }

        if(a > 70 || b > 70 || c > 70) {
            return dp[a][b][c] = solution(70, 70, 70);
        }

        if(a < b && b < c) {
            dp[a][b][c - 1] = solution(a, b, c - 1);
            dp[a][b - 1][c - 1] = solution(a, b - 1, c - 1);
            dp[a][b - 1][c] = solution(a, b - 1, c);
            return dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
        }

        dp[a - 1][b][c] = solution(a - 1, b, c);
        dp[a - 1][b - 1][c] = solution(a - 1, b - 1, c);
        dp[a - 1][b][c - 1] = solution(a - 1, b, c - 1);
        dp[a - 1][b - 1][c - 1] = solution(a - 1, b - 1, c - 1);
        return dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
    }

    private static boolean isEnd(int a, int b, int c) {
        return a == -1 && b == -1 && c == -1;
    }
}