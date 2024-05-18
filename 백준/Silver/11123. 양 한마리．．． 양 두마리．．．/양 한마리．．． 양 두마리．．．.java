import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t;
        t = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < t; test_case++) {
            int h, w;
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            char[][] board = new char[h][w];
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            int answer = solution(h, w, board);
            System.out.println(answer);
        }
    }

    private static int solution(int h, int w, char[][] board) {
        int result = 0;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(!visited[i][j] && board[i][j] == '#') {
                    result++;
                    visited[i][j] = true;
                    dfs(board, i, j, h, w, visited);
                }
            }
        }

        return result;
    }

    private static void dfs(char[][] board, int x, int y, int h, int w, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < h && ny < w && !visited[nx][ny] && board[nx][ny] == '#') {
                visited[nx][ny] = true;
                dfs(board, nx, ny, h, w, visited);
            }
        }
    }
}