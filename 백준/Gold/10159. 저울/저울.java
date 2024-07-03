import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[][] arr1 = new boolean[n + 1][n + 1];
        boolean[][] arr2 = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr1[a][b] = true;
            arr2[b][a] = true;
        }

        solution(n, arr1, arr2);

        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(!arr1[i][j] && !arr2[i][j]) cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution(int n, boolean[][] arr1, boolean[][] arr2) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(arr1[i][k] && arr1[k][j]) {
                        arr1[i][j] = true;
                    }
                    if(arr2[i][k] && arr2[k][j]) {
                        arr2[i][j] = true;
                    }
                }
            }
        }
    }
}