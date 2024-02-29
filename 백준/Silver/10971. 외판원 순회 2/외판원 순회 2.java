import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, answer;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        solution(0, new boolean[N], new int[N]);

        System.out.println(answer);
    }

    private static void solution(int depth, boolean[] visited, int[] arr) {
        if (depth == N) {
            int result = moveAround(arr);
            answer = Math.min(answer, result);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                solution(depth + 1, visited, arr);
                visited[i] = false;
            }
        }
    }

    private static int moveAround(int[] arr) {
        int result = 0;
        int start = arr[0], end = arr[N - 1];
        if (board[end][start] == 0) {
            return Integer.MAX_VALUE;
        }

        for (int i = 0; i < N - 1; i++) {
            int from = arr[i];
            int to = arr[i + 1];

            if (board[from][to] == 0) {
                return Integer.MAX_VALUE;
            }
            result += board[from][to];
        }
        result += board[arr[N - 1]][arr[0]];

        return result;
    }
}