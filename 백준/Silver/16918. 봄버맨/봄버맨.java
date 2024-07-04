import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int R, C, N;
    private static char[][] board;
    private static List<Point> bombs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        bombs = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        int time = 0;
        boolean flag = true;
        while (++time <= N) {
            if (time == 1) {
                continue;
            }

            if (flag) {
                findBomb();
                fullCharge();
                flag = false;
            } else {
                flag = true;
                bomb();
            }
        }

        printBoard();
    }

    private static void fullCharge() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] = 'O';
            }
        }
    }

    private static void bomb() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (Point bomb : bombs) {
            board[bomb.x][bomb.y] = '.';
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + bomb.x;
                int ny = dy[i] + bomb.y;

                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    board[nx][ny] = '.';
                }
            }
        }
    }

    private static void findBomb() {
        bombs = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') {
                    bombs.add(new Point(i, j));
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}