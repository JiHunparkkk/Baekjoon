import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, s, answer;
    static int[] arr;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int depth, int start, int sum) {
        if (depth >= 1 && sum == s) {
            answer++;
        }

        for (int i = start; i < n; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(depth + 1, i + 1, sum + arr[i]);
                visited[i] = 0;
            }
        }
    }
}