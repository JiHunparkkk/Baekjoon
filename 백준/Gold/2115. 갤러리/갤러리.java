import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int M, N;
    private static char[][] board;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][][] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[M][N];
        visited = new boolean[M][N][4];

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
            }
        }
    }

    private static void solution() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    checkValidImage(i, j);
                }
            }
        }
        System.out.println(answer);
    }

    private static void checkValidImage(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isIn(nx, ny) && board[nx][ny] == '.') {
                if (moveUpOrDown(i)) {
                    if (isLeftBlocked(x, y) && isLeftBlocked(nx, ny)
                            && !visited[x][y][LEFT] && !visited[nx][ny][LEFT]) {
                        visited[x][y][LEFT] = true;
                        visited[nx][ny][LEFT] = true;
                        answer++;
                    }
                    if (isRightBlocked(x, y) && isRightBlocked(nx, ny)
                            && !visited[x][y][RIGHT] && !visited[nx][ny][RIGHT]) {
                        visited[x][y][RIGHT] = true;
                        visited[nx][ny][RIGHT] = true;
                        answer++;
                    }
                }

                if (moveLeftOrRight(i)) {
                    if (isUpBlocked(x, y) && isUpBlocked(nx, ny)
                            && !visited[x][y][UP] && !visited[nx][ny][UP]) {
                        visited[x][y][UP] = true;
                        visited[nx][ny][UP] = true;
                        answer++;
                    }
                    if (isDownBlocked(x, y) && isDownBlocked(nx, ny)
                            && !visited[x][y][DOWN] && !visited[nx][ny][DOWN]) {
                        visited[x][y][DOWN] = true;
                        visited[nx][ny][DOWN] = true;
                        answer++;
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    private static boolean moveUpOrDown(int dir) {
        return dir == UP || dir == DOWN;
    }

    private static boolean moveLeftOrRight(int dir) {
        return dir == LEFT || dir == RIGHT;
    }

    private static boolean isLeftBlocked(int x, int y) {
        return isIn(x, y - 1) && board[x][y - 1] == 'X';
    }

    private static boolean isRightBlocked(int x, int y) {
        return isIn(x, y + 1) && board[x][y + 1] == 'X';
    }

    private static boolean isUpBlocked(int x, int y) {
        return isIn(x - 1, y) && board[x - 1][y] == 'X';
    }

    private static boolean isDownBlocked(int x, int y) {
        return isIn(x + 1, y) && board[x + 1][y] == 'X';
    }
}