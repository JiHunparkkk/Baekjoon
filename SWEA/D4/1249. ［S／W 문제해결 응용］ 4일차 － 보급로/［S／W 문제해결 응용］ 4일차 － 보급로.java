import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {

    public static int n;
    public static int[][] board;
    public static int[][] visited;
    public static int[][] weight;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            board = new int[n][n];
            visited = new int[n][n];
            weight = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    weight[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < n; i++) {
                String input = sc.next();
                for (int j = 0; j < n; j++) {
                    board[i][j] = input.charAt(j) - '0';
                }
            }
            dijkstra();

            System.out.println("#" + test_case + " " + weight[n - 1][n - 1]);
        }
    }

    public static void dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return weight[o1.x][o1.y] - weight[o2.x][o2.y];
            }
        });

        pq.offer(new Point(0, 0));
        weight[0][0] = 0;

        while (!pq.isEmpty()) {
            Point poll = pq.poll();
            int x = poll.x;
            int y = poll.y;
            int now_weight = weight[x][y];

            visited[x][y] = 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == 0) {
                    weight[nx][ny] = Math.min(weight[nx][ny], now_weight + board[nx][ny]);
                    pq.offer(new Point(nx, ny));
                }
            }
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}