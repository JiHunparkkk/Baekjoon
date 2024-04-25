import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    private static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<List<Integer>> list = new ArrayList<>();
        int[] indegree = new int[n + 1];

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
        String answer = solution(list, indegree);
        System.out.println(answer);
    }

    private static String solution(List<List<Integer>> list, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int poll = pq.poll();
            if (indegree[poll] <= 0 && !visited[poll]) {
                sb.append(poll).append(" ");
                visited[poll] = true;
            }

            List<Integer> now = list.get(poll);
            for (int i = 0; i < now.size(); i++) {
                int next = now.get(i);

                if (visited[next]) {
                    continue;
                }
                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }

        return sb.toString();
    }
}