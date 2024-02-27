import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean[][] visited;
    private static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M, N;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    queue.add(new Point(i, j));
                }
            }
        }

        int answer = -1;    //무조건 한번은 검사하므로 -1
        visited = new boolean[N][M];
        while (!queue.isEmpty()) {
            bfs(N, M, board);
            answer++;
        }
        long result = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(x -> x == 0)
                .count();
        
        if (result > 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
        br.close();
    }

    private static void bfs(int N, int M, int[][] board) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> nextQ = new ArrayDeque<>();

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            if (!visited[poll.x][poll.y]) {
                visited[poll.x][poll.y] = true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M
                        && !visited[nx][ny] && board[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    board[nx][ny] = 1;
                    nextQ.add(new Point(nx, ny));
                }
            }
        }

        queue = nextQ;
    }
}