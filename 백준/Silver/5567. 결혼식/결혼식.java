import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int n, m;
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }
    }

    private static void solution() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int depth = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                List<Integer> now = list.get(poll);
                for (int j = 0; j < now.size(); j++) {
                    if (!visited[now.get(j)]) {
                        visited[now.get(j)] = true;
                        queue.add(now.get(j));
                        result++;
                    }
                }
            }

            depth++;
            if (depth >= 2) {
                break;
            }
        }

        System.out.println(result);
    }
}