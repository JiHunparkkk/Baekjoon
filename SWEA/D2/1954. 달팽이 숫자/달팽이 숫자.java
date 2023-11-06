import java.util.Scanner;

class Solution {

    static int[] dx = {1, 0, -1, 0};    //우,하,좌,상
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N;
            N = sc.nextInt();

            board = new int[N][N];

            System.out.println("#" + test_case);
            board[0][0] = 1;
            solution(N, 0, 0, false);
            printResult(N);
        }
    }


    private static void solution(int N, int xIndex, int yIndex, boolean flag) {
        for (int i = 0; i < 4; i++) {
            int x = dx[i] + xIndex;
            int y = dy[i] + yIndex;

            if (i == 3) {
                flag = true;
            }

            if (flag) {
                int tx = dx[3] + xIndex;
                int ty = dy[3] + yIndex;

                if (tx >= 0 && ty >= 0 && tx < N && ty < N && board[ty][tx] == 0) {
                    board[ty][tx] += board[yIndex][xIndex] + 1;
                    solution(N, tx, ty, flag);
                    break;
                } else {
                    flag = !flag;
                }
            }

            if (x >= 0 && y >= 0 && x < N && y < N && board[y][x] == 0) {
                board[y][x] += board[yIndex][xIndex] + 1;
                solution(N, x, y, flag);
                break;
            }
        }
    }

    private static void printResult(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}