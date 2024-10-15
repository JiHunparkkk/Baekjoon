import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static List<List<Integer>> list;
    private static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            indegree[second]++;
            list.get(first).add(second);
        }
        System.out.println(solution());
    }

    private static String solution() {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int poll = pq.poll();
            if (!visited[poll] && indegree[poll] == 0) {
                sb.append(poll).append(" ");
                visited[poll] = true;
            }

            List<Integer> now = list.get(poll);
            for (int i = 0; i < now.size(); i++) {
                int next = now.get(i);

                if(visited[next]) {
                    continue;
                }

                indegree[next]--;
                if(indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        return sb.toString();
    }
}