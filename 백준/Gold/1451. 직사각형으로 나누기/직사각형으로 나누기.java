import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[][] board;
    private static long[][] sum;
    private static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = input.charAt(j - 1) - '0';
            }
        }

        sum = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (long) board[i][j];
            }
        }

        case_1();
        case_2();
        case_3();
        case_4();
        case_5();
        case_6();

        System.out.println(answer);
    }


    //가로, 가로, 가로
    private static void case_1() {
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                long row_1 = calSum(1, 1, i, m);
                long row_2 = calSum(i + 1, 1, j, m);
                long row_3 = calSum(j + 1, 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    //세로, 세로, 세로
    private static void case_2() {
        for (int i = 1; i <= m - 2; i++) {
            for (int j = i + 1; j <= m - 1; j++) {
                long row_1 = calSum(1, 1, n, i);
                long row_2 = calSum(1, i + 1, n, j);
                long row_3 = calSum(1, j + 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    //가로
    //세로, 세로
    private static void case_3() {
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= m - 1; j++) {
                long row_1 = calSum(1, 1, i, m);
                long row_2 = calSum(i + 1, 1, n, j);
                long row_3 = calSum(i + 1, j + 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    //세로, 세로
    //가로
    private static void case_4() {
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= m - 1; j++) {
                long row_1 = calSum(1, 1, i, j);
                long row_2 = calSum(1, j + 1, i, m);
                long row_3 = calSum(i + 1, 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    private static void case_5() {
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= m - 1; j++) {
                long row_1 = calSum(1, 1, n, j);
                long row_2 = calSum(1, j + 1, i, m);
                long row_3 = calSum(i + 1, j + 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    private static void case_6() {
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= m - 1; j++) {
                long row_1 = calSum(1, 1, i, j);
                long row_2 = calSum(i + 1, 1, n, j);
                long row_3 = calSum(1, j + 1, n, m);

                answer = Math.max(answer, row_1 * row_2 * row_3);
            }
        }
    }

    private static long calSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }
}