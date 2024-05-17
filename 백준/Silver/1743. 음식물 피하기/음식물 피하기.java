import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int n, m;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        for (int i = 0; i < k; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }

        boolean[][] visited = new boolean[n + 1][m + 1];
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(!visited[i][j] && board[i][j] == 1) {
                    visited[i][j] = true;
                    int result = solution(visited, i, j, 1);
                    answer = Math.max(answer, result);
                }
            }
        }
        System.out.println(answer);
    }

    private static int solution(boolean[][] visited, int x, int y, int depth) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx >= 1 && ny >= 1 && nx <= n && ny <= m && !visited[nx][ny] && board[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    cnt++;
                }
            }
        }

        return cnt;
    }

}