import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] board;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        solution(n / 2, 0, 0);

        System.out.println(sb);
    }

    private static void solution(int size, int x, int y) {

        //전체 검사
        if (check(size * 2, x, y)) {
            sb.append(board[x][y]);
        } else {
            sb.append("(");

            //1사분면
            if (check(size, x, y)) {
                sb.append(board[x][y]);
            } else {
                solution(size / 2, x, y);
            }

            //2사분면
            if (check(size, x, y + size)) {
                sb.append(board[x][y + size]);
            } else {
                solution(size / 2, x, y + size);
            }

            //3사분면
            if (check(size, x + size, y)) {
                sb.append(board[x + size][y]);
            } else {
                solution(size / 2, x + size, y);
            }

            //4사분면
            if (check(size, x + size, y + size)) {
                sb.append(board[x + size][y + size]);
            } else {
                solution(size / 2, x + size, y + size);
            }

            sb.append(")");
        }
    }

    private static boolean check(int size, int x, int y) {
        int now = board[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (now != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
