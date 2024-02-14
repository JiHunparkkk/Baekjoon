import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int r, c, answer;
    private static char[][] board;
    private static int[] dx = {-1, 0, 1};
    private static int[] dy = {1, 1, 1};
    private static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        for (int i = 0; i < r; i++) {
            flag = true;
            dfs(i, 0);
        }
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        if (!flag) {
            return;
        }
        board[x][y] = 'X';

        if (y == c - 1) {
            answer++;
            flag = false;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < r && ny < c && board[nx][ny] == '.') {
                dfs(nx, ny);
            }
        }
    }
}