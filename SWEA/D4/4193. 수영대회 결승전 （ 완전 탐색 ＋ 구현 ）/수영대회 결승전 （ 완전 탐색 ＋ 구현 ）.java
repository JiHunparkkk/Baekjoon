import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    private final static int OBSTACLE = 1;
    private final static int WHIRLPOOL = 2;

    static int[][] board;
    static int[][] visited;
    static int[][] timer;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int x_strt, y_strt, x_end, y_end;
    static int n;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            board = new int[n][n];
            visited = new int[n][n];
            timer = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            x_strt = sc.nextInt();
            y_strt = sc.nextInt();
            x_end = sc.nextInt();
            y_end = sc.nextInt();

            bfs();

            if (timer[x_end][y_end] == 0) {
                timer[x_end][y_end] = -1;
            }

            System.out.println("#" + test_case + " " + timer[x_end][y_end]);
        }
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x_strt, y_strt, 0));
        visited[x_strt][y_strt] = 1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int time = poll.time;

            if (x == x_end && y == y_end) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != OBSTACLE) {

                    if (board[nx][ny] == WHIRLPOOL && time % 3 != 2) {
                        queue.offer(new Point(x, y, time + 1));
                    } else {
                        if (visited[nx][ny] == 1 && timer[nx][ny] <= time + 1) {
                            continue;
                        }
                        timer[nx][ny] = time + 1;
                        visited[nx][ny] = 1;

                        queue.offer(new Point(nx, ny, time + 1));
                    }

                }
            }
        }
    }
}


class Point {
    int x;
    int y;
    int time;

    public Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}