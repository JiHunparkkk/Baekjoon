import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static char[][] board = new char[5][5];
    private static boolean[][] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        visited = new boolean[5][5];
        select(0, 0, 0);
        System.out.println(answer);
    }

    private static void select(int start, int depth, int cnt) {
        if (depth == 7) {
            if (cnt < 4) {
                return;
            }
            if (checkBFS()) {
                answer++;
            }

            return;
        }

        for (int i = start; i < 25; i++) {
            if (!visited[i / 5][i % 5]) {
                visited[i / 5][i % 5] = true;
                if (board[i / 5][i % 5] == 'S') {
                    select(i + 1, depth + 1, cnt + 1);
                } else {
                    select(i + 1, depth + 1, cnt);
                }
                visited[i / 5][i % 5] = false;
            }
        }
    }

    private static boolean checkBFS() {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    queue.add(new int[]{i, j});
                    break;
                }
            }
            if (queue.size() != 0) {
                break;
            }
        }
        boolean[][] check = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                check[i][j] = visited[i][j];
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && check[nx][ny]) {
                    check[nx][ny] = false;
                    cnt++;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return cnt == 7;
    }
}