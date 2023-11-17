import java.util.Scanner;

public class Solution {

    private static final char empty = '.';
    private static final char blocked = '#';

    private static int n;
    private static double max;
    private static char[][] board;
    private static boolean flag;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            n = sc.nextInt();
            board = new char[n][n];

            for (int i = 0; i < n; i++) {
                String input = sc.next();
                board[i] = input.toCharArray();
            }

            String result = "no";
            max = 0;
            flag = false;
            boolean check = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (flag && board[i][j] == blocked) {
                        check = true;
                        break;
                    }
                    dfs(i, j);
                }
                if (flag && check) {
                    break;
                }
            }

            if (!check) {
                if (Math.sqrt(max) % 1 == 0.0 && max != 0) {
                    result = "yes";
                }
            }

            System.out.println("#" + test_case + " " + result);

        }
    }

    private static void dfs(int x, int y) {
        if (board[x][y] == empty) {
            return;
        }

        board[x][y] = empty;
        max++;
        flag = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == blocked) {
                dfs(nx, ny);
            }
        }

    }
}
