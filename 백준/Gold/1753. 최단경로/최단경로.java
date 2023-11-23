import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static List<List<Node>> list;
    private static boolean[] visited;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        visited = new boolean[v + 1];
        dist = new int[v + 1];
        list = new ArrayList<>();
        for (int i = 1; i < v + 1; i++) {
            list.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }
        list.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
        }
        solution(k);

        for (int i = 1; i < v + 1; i++) {
            int d = dist[i];
            System.out.println(d == Integer.MAX_VALUE ? "INF" : d);
        }

    }

    private static void solution(int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(k, 0));
        dist[k] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.index]) {
                visited[now.index] = true;
            }

            for (Node next : list.get(now.index)) {
                if (!visited[next.index] && dist[next.index] > dist[now.index] + next.weight) {
                    dist[next.index] = dist[now.index] + next.weight;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
