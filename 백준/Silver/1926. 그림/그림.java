import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n, m, max, cnt;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }

    private static void solution() {
        boolean[][] visited = new boolean[n][m];
        int totalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    visited[i][j] = true;
                    totalCnt++;

                    cnt = 1;
                    dfs(visited, i, j);
                    max = Math.max(max, cnt);
                }
            }
        }

        System.out.println(totalCnt);
        System.out.println(max);
    }

    private static void dfs(boolean[][] visited, int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || board[nx][ny] != 1) {
                continue;
            }

            visited[nx][ny] = true;
            cnt++;
            dfs(visited, nx, ny);
        }
    }
}