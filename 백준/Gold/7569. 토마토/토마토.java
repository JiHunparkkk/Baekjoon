import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static class Point {
        int z, x, y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    private static int M, N, H;
    private static int[][][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][N][M];
        Queue<Point> queue = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    if (board[i][j][k] == 1) {
                        queue.add(new Point(i, j, k));
                    }
                }
            }
        }
        int answer = bfs(queue);
        System.out.println(answer);
    }

    private static int bfs(Queue<Point> queue) {
        int[] dz = {1, -1, 0, 0, 0, 0};
        int[] dx = {0, 0, -1, 0, 1, 0};
        int[] dy = {0, 0, 0, 1, 0, -1};
        int answer = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            answer++;

            //하루 단위
            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();

                for (int j = 0; j < 6; j++) {
                    int nz = poll.z + dz[j];
                    int nx = poll.x + dx[j];
                    int ny = poll.y + dy[j];

                    if (isIn(nz, nx, ny) && board[nz][nx][ny] == 0) {
                        board[nz][nx][ny] = 1;
                        queue.add(new Point(nz, nx, ny));
                    }
                }
            }
        }

        if (!isCompleted()) {
            return -1;
        }

        return answer;
    }

    private static boolean isIn(int nz, int nx, int ny) {
        return nz >= 0 && nx >= 0 && ny >= 0 && nz < H && nx < N && ny < M;
    }

    private static boolean isCompleted() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (board[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}