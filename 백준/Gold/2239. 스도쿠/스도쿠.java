import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Point> zero = new ArrayList<>();
        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
                if (board[i][j] == 0) {
                    zero.add(new Point(i, j));
                }
            }
        }

        sudoku(zero, 0);
    }

    private static void sudoku(List<Point> zero, int idx) { //idx는 현재 검사중인 0의 인덱스
        if (zero.size() == idx) {   //모든 0의 숫자를 검사했으면 출력 및 종료
            printResult();
            System.exit(0);
        }

        Point now = zero.get(idx);
        for (int i = 1; i <= 9; i++) {
            if (validPosition(now, i)) {    //겹치는 숫자가 있으면 백트래킹
                board[now.x][now.y] = i;
                sudoku(zero, idx + 1);
                board[now.x][now.y] = 0;
            }
        }
    }

    private static boolean validPosition(Point now, int num) {
        int x = now.x;
        int y = now.y;

        //행, 열 검사
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num || board[i][y] == num) {
                return false;
            }
        }

        //구역 검사 (시작점 초기화)
        x = x / 3 * 3;
        y = y / 3 * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printResult() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}