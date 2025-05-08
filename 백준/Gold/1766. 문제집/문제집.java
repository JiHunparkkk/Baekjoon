import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[] degree;
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            degree[b]++;
            list.get(a).add(b);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int poll = pq.poll();

            sb.append(poll).append(" ");

            for(int i = 0; i < list.get(poll).size(); i++) {
                int next = list.get(poll).get(i);
                degree[next]--;
                if(degree[next] == 0) {
                    pq.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}