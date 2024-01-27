import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private final static int KIND = 3;
    private static int n, m, answer = 0;
    private static int[][] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution(0);

        System.out.println(answer);
    }

    private static void solution(int depth) {
        if (depth == KIND) {
            answer = Math.max(answer, sumSatisfy());
            return;
        }

        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                solution(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static int sumSatisfy() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                if (visited[j]) {
                    max = Math.max(arr[i][j], max);
                }
            }
            sum += max;
        }

        return sum;
    }
}