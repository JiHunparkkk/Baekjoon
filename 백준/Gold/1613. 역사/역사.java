import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static boolean[][] first;
    private static boolean[][] second;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        first = new boolean[n + 1][n + 1];
        second = new boolean[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            first[a][b] = true;
            second[b][a] = true;
        }

        checkConn(n);

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findAnswer(a, b, n)).append("\n");
        }
        System.out.println(sb);
    }

    private static void checkConn(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(first[i][k] && first[k][j]) {
                        first[i][j] = true;
                    }
                    if(second[i][k] && second[k][j]) {
                        second[i][j] = true;
                    }
                }
            }
        }
    }

    private static int findAnswer(int a, int b, int n) {
        if(first[a][b]) {
            return -1;
        }

        if(second[a][b]) {
            return 1;
        }

        return 0;
    }
}