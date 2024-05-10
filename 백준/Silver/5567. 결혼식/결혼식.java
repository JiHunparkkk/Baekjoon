import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
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

        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        for (int i = 0; i < list.get(1).size(); i++) {
            if (!visited[list.get(1).get(i)]) {
                answer++;
                visited[list.get(1).get(i)] = true;
            }

            int friend = list.get(1).get(i);
            for (int j = 0; j < list.get(friend).size(); j++) {
                if (visited[list.get(friend).get(j)]) {
                    continue;
                }
                visited[list.get(friend).get(j)] = true;
                answer++;
            }
        }
        System.out.println(answer);
    }
}