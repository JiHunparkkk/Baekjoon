import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m, r;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (List<Integer> integers : list) {
            Collections.sort(integers, Collections.reverseOrder());
        }

        int[] result = solution(n, m, r, list);
        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

    private static int[] solution(int n, int m, int r, List<List<Integer>> list) {
        int[] result = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(r);
        visited[r] = true;
        result[r] = 1;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            result[poll] = cnt++;
            List<Integer> now = list.get(poll);

            for (int i = 0; i < now.size(); i++) {
                int next = now.get(i);

                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                queue.add(next);
            }
        }
        return result;
    }
}