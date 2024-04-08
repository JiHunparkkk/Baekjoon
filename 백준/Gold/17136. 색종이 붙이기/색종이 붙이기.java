import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int LEN = 10;
    private static int[][] arr;
    private static int[] paper = {0, 5, 5, 5, 5, 5};
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[LEN][LEN];
        for (int i = 0; i < LEN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < LEN; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0, 0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    private static void dfs(int idx, int cnt) {
        //0,0 ~ 10, 10
        if (idx == LEN * LEN) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (cnt >= answer) {
            return;
        }

        int x = idx / LEN;
        int y = idx % LEN;

        if (arr[x][y] == 1) {
            for (int i = 5; i > 0; i--) {
                if (paper[i] > 0 && validSquare(x, y, i)) {
                    paper[i] -= 1;
                    fill(x, y, i, 0);
                    dfs(idx + 1, cnt + 1);
                    fill(x, y, i, 1);
                    paper[i] += 1;
                }
            }
        } else {
            dfs(idx + 1, cnt);
        }
    }

    private static boolean validSquare(int x, int y, int idx) {
        for (int i = x; i < x + idx; i++) {
            for (int j = y; j < y + idx; j++) {
                if (i >= LEN || j >= LEN || arr[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void fill(int x, int y, int idx, int num) {
        for (int i = x; i < x + idx; i++) {
            for (int j = y; j < y + idx; j++) {
                arr[i][j] = num;
            }
        }
    }
}