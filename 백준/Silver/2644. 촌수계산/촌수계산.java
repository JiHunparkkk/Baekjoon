import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n, answer;
    private static List<List<Integer>> list = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < 101; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        visited = new boolean[n + 1];
        visited[tx] = true;
        answer = -1;
        dfs(tx, ty, 0);
        System.out.println(answer);
    }

    private static void dfs(int start, int end, int cnt) {
        if (start == end) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < list.get(start).size(); i++) {
            int num = list.get(start).get(i);
            if (!visited[num]) {
                visited[num] = true;
                dfs(num, end, cnt + 1);
                visited[num] = false;
            }
        }
    }
}