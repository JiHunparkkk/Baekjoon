import java.util.Scanner;

public class Solution {

    private static final char empty = '.';
    private static final char blocked = '#';

    private static int n;
    private static int len;
    private static String result;
    private static char[][] board;

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
            len = 0;
            result = "yes";
            boolean first = true;
            int first_x = 0, first_y = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == blocked && first) {
                        first_x = i;
                        first_y = j;
                        first = false;
                    }
                    if (board[i][j] == blocked) {
                        if (j == first_y) {
                            j = right(i, j);
                        } else {
                            result = "no";
                        }
                    }
                    if (board[i][j] == blocked && ((i + 1 < n && board[i + 1][j] == empty) || i == n - 1)) {
                        if (i - first_x + 1 != len) {
                            result = "no";
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static int right(int x, int y) {
        //오른쪽으로 감
        int i;
        for (i = y + 1; i < n; i++) {
            if (board[x][i] == empty) {
                break;
            }
        }

        //길이를 구함
        if (len == 0) {
            len = i - y;
        } else if (len != i - y) {
            result = "no";
        }
        return i - 1;
    }
}
