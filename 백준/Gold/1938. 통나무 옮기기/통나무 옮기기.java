import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Main {

    private static class Point {
        int x, y;
        int status;

        public Point(int x, int y, int status) {
            this.x = x;
            this.y = y;
            this.status = status;
        }
    }

    private static final int ROW = 1;
    private static final int COL = 2;

    private static int N;
    private static char[][] board;
    private static boolean[][][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        visited = new boolean[N][N][3];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        //중점 찾기
        Point mid = findMid();
        int answer = bfs(mid);
        System.out.println(answer);
    }

    private static Point findMid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'B') {
                    if (isIn(i, j + 1) && board[i][j + 1] == 'B') {
                        visited[i][j + 1][ROW] = true;
                        return new Point(i, j + 1, ROW);
                    }
                    if (isIn(i + 1, j) && board[i + 1][j] == 'B') {
                        visited[i + 1][j][COL] = true;
                        return new Point(i + 1, j, COL);
                    }
                }
            }
        }
        return new Point(0, 0, 0);
    }

    private static int bfs(Point point) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(point);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();

                if (board[now.x][now.y] == 'E' && isArrived(now)) {
                    return result;
                }

                //오른쪽
                if (moveRight(now)) {
                    queue.add(new Point(now.x, now.y + 1, now.status));
                }

                //왼쪽
                if (moveLeft(now)) {
                    queue.add(new Point(now.x, now.y - 1, now.status));
                }

                //위쪽
                if (moveUp(now)) {
                    queue.add(new Point(now.x - 1, now.y, now.status));
                }

                //아래쪽
                if (moveDown(now)) {
                    queue.add(new Point(now.x + 1, now.y, now.status));
                }

                //회전
                if (moveCurl(now)) {
                    if (now.status == ROW) {
                        queue.add(new Point(now.x, now.y, COL));
                    } else {
                        queue.add(new Point(now.x, now.y, ROW));
                    }
                }
            }
            result++;
        }
        return 0;
    }


    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
    }

    private static boolean isArrived(Point now) {
        int x = now.x;
        int y = now.y;
        if (now.status == ROW) {
            return board[x][y - 1] == 'E' && board[x][y + 1] == 'E';
        }
        if (now.status == COL) {
            return board[x - 1][y] == 'E' && board[x + 1][y] == 'E';
        }
        return false;
    }

    private static boolean moveRight(Point now) {
        int x = now.x;
        int y = now.y;

        if (now.status == ROW) {
            if (isIn(x, y + 2) && board[x][y + 2] != '1' && !visited[x][y + 1][ROW]) {
                visited[x][y + 1][ROW] = true;
                return true;
            }
        } else if (now.status == COL) {
            for (int i = -1; i < 2; i++) {
                if (!isIn(x + i, y + 1) || board[x + i][y + 1] == '1') {
                    return false;
                }
            }
            if (!visited[x][y + 1][COL]) {
                visited[x][y + 1][COL] = true;
                return true;
            }
        }

        return false;
    }

    private static boolean moveLeft(Point now) {
        int x = now.x;
        int y = now.y;

        if (now.status == ROW) {
            if (isIn(x, y - 2) && board[x][y - 2] != '1' && !visited[x][y - 1][ROW]) {
                visited[x][y - 1][ROW] = true;
                return true;
            }
        } else if (now.status == COL) {
            for (int i = -1; i < 2; i++) {
                if (!(isIn(x + i, y - 1) && board[x + i][y - 1] != '1')) {
                    return false;
                }
            }
            if (!visited[x][y - 1][COL]) {
                visited[x][y - 1][COL] = true;
                return true;
            }
        }
        return false;
    }

    private static boolean moveUp(Point now) {
        int x = now.x;
        int y = now.y;

        if (now.status == COL) {
            if (isIn(x - 2, y) && board[x - 2][y] != '1' && !visited[x - 1][y][COL]) {
                visited[x - 1][y][COL] = true;
                return true;
            }
        } else if (now.status == ROW) {
            for (int i = -1; i < 2; i++) {
                if (!(isIn(x - 1, y + i) && board[x - 1][y + i] != '1')) {
                    return false;
                }
            }
            if (!visited[x - 1][y][ROW]) {
                visited[x - 1][y][ROW] = true;
                return true;
            }

        }

        return false;
    }

    private static boolean moveDown(Point now) {
        int x = now.x;
        int y = now.y;

        if (now.status == COL) {
            if (isIn(x + 2, y) && board[x + 2][y] != '1' && !visited[x + 1][y][COL]) {
                visited[x + 1][y][COL] = true;
                return true;
            }
        } else if (now.status == ROW) {
            for (int i = -1; i < 2; i++) {
                if (!isIn(x + 1, y + i) || board[x + 1][y + i] == '1') {
                    return false;
                }
            }
            if (!visited[x + 1][y][ROW]) {
                visited[x + 1][y][ROW] = true;
                return true;
            }
        }

        return false;
    }

    private static boolean moveCurl(Point now) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int nx = now.x + i;
                int ny = now.y + j;

                if (!isIn(nx, ny) || board[nx][ny] == '1') {
                    return false;
                }
            }
        }

        if (now.status == ROW && !visited[now.x][now.y][COL]) {
            visited[now.x][now.y][COL] = true;
            return true;
        } else if (now.status == COL && !visited[now.x][now.y][ROW]) {
            visited[now.x][now.y][ROW] = true;
            return true;
        }
        return false;
    }
}