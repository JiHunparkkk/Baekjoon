import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] board;
    private static int H, W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //(시작점)이동 시켜놓고, 벽의 여부 판단
        //시작점에서 H - 1(세로), W - 1(가로) 확인
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int S1 = Integer.parseInt(st.nextToken());
        int S2 = Integer.parseInt(st.nextToken());
        int E1 = Integer.parseInt(st.nextToken());
        int E2 = Integer.parseInt(st.nextToken());
        Point start = new Point(S1, S2);
        Point end = new Point(E1, E2);

        int answer = bfs(start, end);
        System.out.println(answer);
    }

    private static int bfs(Point start, Point end) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[start.x][start.y] = true;

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();

                //종료 조건
                if(poll.x == end.x && poll.y == end.y) {
                    return result;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = poll.x + dx[j];
                    int ny = poll.y + dy[j];

                    if (isIn(nx, ny) && canMove(nx, ny, visited)) {
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
            result++;
        }

        return -1;
    }

    private static boolean isIn(int nx, int ny) {
        boolean start = nx >= 1 && nx <= N && ny >= 1 && ny <= M;
        boolean bottom = nx + H - 1 <= N;
        boolean right = ny + W - 1 <= M;

        return start && right && bottom;
    }

    private static boolean canMove(int nx, int ny, boolean[][] visited) {
        if(visited[nx][ny] || board[nx][ny] == 1) {
            return false;
        }

        //오른쪽
        for (int i = 0; i < H; i++) {
            if(board[nx + i][ny + W - 1] == 1){
                return false;
            }
        }

        //아래
        for (int i = 0; i < W; i++) {
            if (board[nx + H - 1][ny + i] == 1) {
                return false;
            }
        }

        //왼쪽
        for (int i = 0; i < H; i++) {
            if (board[nx + i][ny] == 1) {
                return false;
            }
        }

        //위
        for (int i = 0; i < W; i++) {
            if (board[nx][ny + i] == 1) {
                return false;
            }
        }

        return true;
    }
}