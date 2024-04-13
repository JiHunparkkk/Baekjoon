import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    private static int n, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //최대 5번 이동, 4 ^ 5 반복

        solution(board, 0);
        System.out.println(answer);
    }

    private static void solution(int[][] board, int depth) {
        //만약 이동했는데 변화가 없다면 다음 단계로 가지 않음
        answer = Math.max(answer, findMax(board));
//        testPrint(board);
        if (depth == 5) {
            return;
        }

        int[][] result = moveUp(copyArray(board));
        if (!isSame(board, result)) {
            solution(result, depth + 1);
        }

        result = moveDown(copyArray(board));
        if (!isSame(board, result)) {
            solution(result, depth + 1);
        }

        result = moveLeft(copyArray(board));
        if (!isSame(board, result)) {
            solution(result, depth + 1);
        }

        result = moveRight(copyArray(board));
        if (!isSame(board, result)) {
            solution(result, depth + 1);
        }
    }

    private static int[][] moveUp(int[][] board) {
        //이동시킨 후, 결합
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    stack.add(board[j][i]);
                }
            }

            //이동 결과 반영
            for (int j = 0; j < n; j++) {
                if (stack.size() >= 2 && stack.peek().equals(stack.get(stack.size() - 2))) {
                    board[j][i] = stack.pop() + stack.pop();
                } else if (!stack.isEmpty()) {
                    board[j][i] = stack.pop();
                } else {
                    board[j][i] = 0;
                }
            }
        }

        return board;
    }

    private static int[][] moveDown(int[][] board) {
        //이동시킨 후, 결합
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] != 0) {
                    stack.add(board[j][i]);
                }
            }

            //이동 결과 반영
            for (int j = n - 1; j >= 0; j--) {
                if (stack.size() >= 2 && stack.peek().equals(stack.get(stack.size() - 2))) {
                    board[j][i] = stack.pop() + stack.pop();
                } else if (!stack.isEmpty()) {
                    board[j][i] = stack.pop();
                } else {
                    board[j][i] = 0;
                }
            }
        }

        return board;
    }

    private static int[][] moveLeft(int[][] board) {
        //이동시킨 후, 결합
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    stack.add(board[i][j]);
                }
            }

            //이동 결과 반영
            for (int j = 0; j < n; j++) {
                if (stack.size() >= 2 && stack.peek().equals(stack.get(stack.size() - 2))) {
                    board[i][j] = stack.pop() + stack.pop();
                } else if (!stack.isEmpty()) {
                    board[i][j] = stack.pop();
                } else {
                    board[i][j] = 0;
                }
            }
        }

        return board;
    }

    private static int[][] moveRight(int[][] board) {
        //이동시킨 후, 결합
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    stack.add(board[i][j]);
                }
            }

            //이동 결과 반영
            for (int j = n - 1; j >= 0; j--) {
                if (stack.size() >= 2 && stack.peek().equals(stack.get(stack.size() - 2))) {
                    board[i][j] = stack.pop() + stack.pop();
                } else if (!stack.isEmpty()) {
                    board[i][j] = stack.pop();
                } else {
                    board[i][j] = 0;
                }
            }
        }

        return board;
    }

    private static int[][] copyArray(int[][] board) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    private static int findMax(int[][] board) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Arrays.stream(board[i]).max().getAsInt());
        }
        return max;
    }

    private static boolean isSame(int[][] origin, int[][] copy) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (origin[i][j] != copy[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void testPrint(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("========================");
    }
}