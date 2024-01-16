import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, answer;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n];

        answer = 0;
        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int depth) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;

            if (possiblity(depth)) {
                dfs(depth + 1);
            }
        }
    }

    private static boolean possiblity(int col) {
        for (int i = 0; i < col; i++) {
            //가로, 세로 확인
            if (board[col] == board[i]) {
                return false;
            }

            //대각선 확인
            if (Math.abs(i - col) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }
        return true;
    }


}