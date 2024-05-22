import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int w, h;
    private static int[][] board;
    private static int[] dx = {-1, 0, 1 ,0, -1, -1, 1, 1};
    private static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;
            board = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println(solution());
        }
    }

    private static int solution() {
        boolean[][] visited = new boolean[h][w];
        int result = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    visited[i][j] = true;
                    dfs(i, j, visited);
                    result++;
                }
            }
        }
        return result;
    }

    private static void dfs(int x, int y, boolean[][] visited) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >=0 && ny >= 0 && nx < h && ny < w && !visited[nx][ny] && board[nx][ny] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny, visited);
            }
        }
    }
}