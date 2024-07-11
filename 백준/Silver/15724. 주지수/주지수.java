import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeSum();

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int xs = Integer.parseInt(st.nextToken());
            int ys = Integer.parseInt(st.nextToken());
            int xe = Integer.parseInt(st.nextToken());
            int ye = Integer.parseInt(st.nextToken());

            System.out.println(solution(xs, ys, xe, ye));
        }
    }

    private static void makeSum() {
        int[][] newBoard = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                newBoard[i][j] = board[i][j] + newBoard[i - 1][j] + newBoard[i][j - 1] - newBoard[i - 1][j - 1];
            }
        }
        board = newBoard;
    }

    private static int solution(int xs, int ys, int xe, int ye) {
        return board[xe][ye] - board[xs - 1][ye] - board[xe][ys - 1] + board[xs - 1][ys - 1];
    }
}