import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, answer;
    private static char[][] board;
    private static boolean[] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        visited[board[0][0] - 'A'] = true;
        dfs(1, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int depth, int x, int y) {
        answer = Math.max(depth, answer);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[board[nx][ny] - 'A']) {
                int index = board[nx][ny] - 'A';
                visited[index] = true;
                dfs(depth + 1, nx, ny);
                visited[index] = false;
            }
        }
    }
}