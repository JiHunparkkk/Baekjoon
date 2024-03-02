import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

    private static int n, m;
    private static int[][] board;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        Point start = null;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    start = new Point(i, j);
                }
            }
        }

        int[][] answer = bfs(start);
        afterCheck(answer);
        for (int[] ints : answer) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static int[][] bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        int[][] visited = new int[n][m];
        init(visited);
        visited[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny] == -1) {
                    if (board[nx][ny] == 0) {
                        visited[nx][ny] = 0;
                    } else {
                        visited[nx][ny] = visited[poll.x][poll.y] + 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        return visited;
    }

    private static void init(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = -1;
            }
        }
    }

    private static void afterCheck(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 && arr[i][j] == -1) {
                    arr[i][j] = 0;
                }
            }
        }
    }
}