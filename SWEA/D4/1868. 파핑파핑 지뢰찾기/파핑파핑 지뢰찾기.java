import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

    static char[][] board;
    static int[][] visited;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int n, answer;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            board = new char[n][n];
            visited = new int[n][n];

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < n; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0 && board[i][j] == '.') {
                        answer += findZero(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0 && board[i][j] == '.') {
                        answer++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
        br.close();
    }

    static int findZero(int x, int y) {

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == '*') {
                return 0;
            }
        }

        visited[x][y] = 1;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == 0) {
                visited[nx][ny] = 1;
                findZero(nx, ny);
            }
        }
        return 1;
    }
}