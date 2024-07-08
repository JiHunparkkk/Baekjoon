import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static char[][] board;
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            init();
            System.out.println(solution());
        }

    }

    private static void init() throws IOException {
        board = new char[3][3];
        set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }
    }

    private static int solution() {
        Queue<char[][]> queue = new ArrayDeque<>();
        queue.add(board);
        set.add(charToString(board));

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                char[][] poll = queue.poll();

                if (isAllSame(poll)) {
                    return result;
                }

                //각 가로, 세로, 대각선으로 다 뒤집으면서 넣기
                changeRow(queue, poll);
                changeCol(queue, poll);
                changeDiagonal(queue, poll);
            }
            result++;
        }

        return -1;
    }

    private static boolean isAllSame(char[][] poll) {
        char first = poll[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (first != poll[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    //가로
    private static void changeRow(Queue<char[][]> queue, char[][] poll) {
        for (int i = 0; i < 3; i++) {
            char[][] newBoard = copyBoard(poll);
            for (int j = 0; j < 3; j++) {
                newBoard[i][j] = changeWord(newBoard[i][j]);
            }

            String str = charToString(newBoard);
            if (!set.contains(str)) {
                set.add(str);
                queue.add(newBoard);
            }
        }
    }

    //세로
    private static void changeCol(Queue<char[][]> queue, char[][] poll) {
        for (int i = 0; i < 3; i++) {
            char[][] newBoard = copyBoard(poll);
            for (int j = 0; j < 3; j++) {
                newBoard[j][i] = changeWord(newBoard[j][i]);
            }

            String str = charToString(newBoard);
            if (!set.contains(str)) {
                set.add(str);
                queue.add(newBoard);
            }
        }
    }

    //대각선
    private static void changeDiagonal(Queue<char[][]> queue, char[][] poll) {
        char[][] newBoard = copyBoard(poll);
        newBoard[0][0] = changeWord(newBoard[0][0]);
        newBoard[1][1] = changeWord(newBoard[1][1]);
        newBoard[2][2] = changeWord(newBoard[2][2]);

        String str = charToString(newBoard);
        if (!set.contains(str)) {
            set.add(str);
            queue.add(newBoard);
        }

        newBoard = copyBoard(poll);
        newBoard[0][2] = changeWord(newBoard[0][2]);
        newBoard[1][1] = changeWord(newBoard[1][1]);
        newBoard[2][0] = changeWord(newBoard[2][0]);

        str = charToString(newBoard);
        if (!set.contains(str)) {
            set.add(str);
            queue.add(newBoard);
        }
    }

    private static char[][] copyBoard(char[][] origin) {
        char[][] newBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard[i][j] = origin[i][j];
            }
        }
        return newBoard;
    }

    private static char changeWord(char word) {
        if (word == 'H') {
            return 'T';
        }
        return 'H';
    }

    private static String charToString(char[][] origin) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(origin[i][j]);
            }
        }
        return sb.toString();
    }
}