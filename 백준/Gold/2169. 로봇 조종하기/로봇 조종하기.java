import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solution() {
        int[][][] dp = new int[N][M][3];    //0:왼, 1:오, 2:위

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -101 * N * M;
                }
            }
        }

        dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = board[0][0];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j > 0) {
                    dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][2]) + board[i][j];
                }
                if (i > 0) {
                    dp[i][j][2] = Math.max(dp[i - 1][j][0], Math.max(dp[i - 1][j][1], dp[i - 1][j][2])) + board[i][j];
                }
            }
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j][1] = Math.max(dp[i][j + 1][1], dp[i][j + 1][2]) + board[i][j];
            }
        }

        System.out.println(Math.max(dp[N - 1][M - 1][0], Math.max(dp[N - 1][M - 1][1], dp[N - 1][M - 1][2])));
    }

}
