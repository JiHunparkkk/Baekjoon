import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static class Core {
        int x, y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, answer, maxCoreCnt;
    private static int[][] board;
    private static List<Core> cores;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        cores.add(new Core(i, j));
                    }
                }
            }

            answer = Integer.MAX_VALUE;
            maxCoreCnt = 0;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void dfs(int depth, int lineCnt, int coreCnt) {
        if (depth == cores.size()) {
            if (maxCoreCnt < coreCnt) {
                maxCoreCnt = coreCnt;
                answer = lineCnt;
            } else if (maxCoreCnt == coreCnt) {
                answer = Math.min(answer, lineCnt);
            }
            return;
        }

        Core core = cores.get(depth);
        for (int i = 0; i < 4; i++) {
            int nx = core.x + dx[i];
            int ny = core.y + dy[i];

            if (isEdge(core.x, core.y)) { //가장자리에 있을 경우 바로 처리
                dfs(depth + 1, lineCnt, coreCnt + 1);
                break;
            }

            if (validLine(nx, ny, i)) {
                int result = setLine(nx, ny, i);
                dfs(depth + 1, result + lineCnt, coreCnt + 1);
                resetLine(nx, ny, i);
            } else {
                dfs(depth + 1, lineCnt, coreCnt);
            }
        }

    }

    private static boolean isEdge(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                return true;
            }
        }
        return false;
    }

    private static boolean validLine(int nx, int ny, int dir) {
        while (validRange(nx, ny)) {
            if (Math.abs(board[nx][ny]) == 1) {   //코어(1) 또는 전선(-1)을 만나면 종료
                return false;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
        return true;
    }

    private static boolean validRange(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
    }

    private static int setLine(int nx, int ny, int dir) {
        int cnt = 0;
        while (validRange(nx, ny)) {
            cnt++;
            board[nx][ny] = -1;
            nx += dx[dir];
            ny += dy[dir];
        }
        return cnt;
    }

    private static void resetLine(int nx, int ny, int dir) {
        while (validRange(nx, ny)) {
            board[nx][ny] = 0;
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}
