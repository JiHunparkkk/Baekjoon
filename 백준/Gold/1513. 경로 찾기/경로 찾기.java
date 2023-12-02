import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int MOD = 1_000_007;
    private static int[][] board;
    private static int[][][][] dp;
    private static int n, m, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            board[a][b] = i + 1;
        }

        dp = new int[51][51][51][51];
        for (int[][][] ints : dp) {
            for (int[][] anInt : ints) {
                for (int[] ints1 : anInt) {
                    Arrays.fill(ints1, -1);
                }
            }
        }

        for (int i = 0; i <= c; i++) {
            sb.append(solution(1, 1, i, 0)).append(" ");
        }

        bw.write(sb.toString());

        br.close();
        bw.close();
    }

    private static int solution(int x, int y, int cnt, int prev) {
        if (x > n || y > m) {
            return 0;
        }

        if (x == n && y == m) {
            if (cnt == 0 && board[x][y] == 0) {
                return 1;
            }
            if (cnt == 1 && board[x][y] > prev) {
                return 1;
            }
            return 0;
        }

        if (cnt < 0) {
            return 0;
        }
        if (dp[x][y][cnt][prev] != -1) {
            return dp[x][y][cnt][prev];
        }

        dp[x][y][cnt][prev] = 0;
        if (board[x][y] == 0) {
            dp[x][y][cnt][prev] = (solution(x + 1, y, cnt, prev) + solution(x, y + 1, cnt, prev)) % MOD;
        } else if (board[x][y] > prev) {
            dp[x][y][cnt][prev] =
                    (solution(x + 1, y, cnt - 1, board[x][y]) + solution(x, y + 1, cnt - 1, board[x][y])) % MOD;
        }
        return dp[x][y][cnt][prev];
    }
}