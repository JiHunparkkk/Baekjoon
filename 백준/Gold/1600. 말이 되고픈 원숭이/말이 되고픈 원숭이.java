import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int x, y, horseCnt, moveCnt;

        public Point(int x, int y, int horseCnt, int moveCnt) {
            this.x = x;
            this.y = y;
            this.horseCnt = horseCnt;
            this.moveCnt = moveCnt;
        }
    }

    private static int[][] horse = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
    private static int[][] monkey = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int K, W, H;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new int[W][H];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < H; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 0, 0));

        boolean[][][] visited = new boolean[W][H][K + 1];
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            if (x == W - 1 && y == H - 1) {
                return poll.moveCnt;
            }

            //원숭이처럼 이동
            for (int z = 0; z < 4; z++) {
                int nx = x + monkey[z][0];
                int ny = y + monkey[z][1];

                if (isIn(nx, ny) && !visited[nx][ny][poll.horseCnt]) {
                    visited[nx][ny][poll.horseCnt] = true;
                    queue.add(new Point(nx, ny, poll.horseCnt, poll.moveCnt + 1));
                }
            }

            //말처럼 이동
            if (poll.horseCnt < K) {
                for (int z = 0; z < 8; z++) {
                    int nx = x + horse[z][0];
                    int ny = y + horse[z][1];

                    if (isIn(nx, ny) && !visited[nx][ny][poll.horseCnt + 1]) {
                        visited[nx][ny][poll.horseCnt + 1] = true;
                        queue.add(new Point(nx, ny, poll.horseCnt + 1, poll.moveCnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < W && ny < H
                && board[nx][ny] != 1;
    }
}


