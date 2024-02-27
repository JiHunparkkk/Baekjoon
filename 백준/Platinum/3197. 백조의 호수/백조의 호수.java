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

    private static int R, C;
    private static char[][] board;
    private static Point start;
    private static Point end;
    private static Queue<Point> water;
    private static Queue<Point> bird;
    private static boolean[][] visit_water;
    private static boolean[][] visit_bird;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean success;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        water = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'L') {
                    if (start == null) {
                        start = new Point(i, j);
                    } else {
                        end = new Point(i, j);
                    }
                }
                if (board[i][j] != 'X') {
                    water.add(new Point(i, j));
                }
            }
        }

        bird = new ArrayDeque<>();
        bird.add(start);
        visit_water = new boolean[R][C];
        visit_bird = new boolean[R][C];
        int answer = 0;

        //더 이상 녹일게 없거나, 이동할 곳이 없으면 만날 수 있는 것이므로 종료
        while (!bird.isEmpty() || !water.isEmpty()) {
            move();

            if (bird.isEmpty() || success) {
                break;
            } else {
                melt();
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void move() {
        Queue<Point> queue = new ArrayDeque<>();

        while (!bird.isEmpty()) {
            Point poll = bird.poll();

            if (!visit_bird[poll.x][poll.y]) {
                visit_bird[poll.x][poll.y] = true;
            }

            if (poll.x == end.x && poll.y == end.y) {
                success = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (isIn(nx, ny) && !visit_bird[nx][ny]) {
                    visit_bird[nx][ny] = true;
                    if (board[nx][ny] != 'X') {
                        bird.add(new Point(nx, ny));
                    } else {
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        bird = queue;
    }

    private static void melt() {
        Queue<Point> queue = new ArrayDeque<>();

        while (!water.isEmpty()) {
            Point poll = water.poll();

            if (!visit_water[poll.x][poll.y]) {
                visit_water[poll.x][poll.y] = true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (isIn(nx, ny) && !visit_water[nx][ny] && board[nx][ny] == 'X') {
                    visit_water[nx][ny] = true;
                    board[nx][ny] = '.';
                    queue.add(new Point(nx, ny));
                }
            }
        }
        water = queue;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < R && ny < C;
    }
}