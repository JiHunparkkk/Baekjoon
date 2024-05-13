import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int n, m;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Queue<Pair> queue = new ArrayDeque<>();
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 1) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        int[][] distance = checkDistance(queue);
        int answer = findMaxDistance(distance);
        System.out.println(answer);
    }

    private static int[][] checkDistance(Queue<Pair> queue) {
        int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], 51);
        }

        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            Queue<Pair> next = new ArrayDeque<>();
            next.add(now);
            distance[now.x][now.y] = 0;

            while (!next.isEmpty()) {
                Pair poll = next.poll();
                for (int i = 0; i < 8; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];

                    if (!isIn(nx, ny) || board[nx][ny] == 1) {
                        continue;
                    }
                    if(distance[nx][ny] > distance[poll.x][poll.y] + 1){
                        distance[nx][ny] = distance[poll.x][poll.y] + 1;
                        next.add(new Pair(nx, ny));
                    }
                }
            }
        }

        return distance;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }

    private static int findMaxDistance(int[][] distance) {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = Math.max(result, distance[i][j]);
            }
        }
        return result;
    }
}