import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int[] parent;
    private static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        //자기자신을 가리키는 사람을 부모노드로 가지고 있으면 팀 성립 x
        for (int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            parent = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                parent[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            boolean[] visited = new boolean[n + 1];
            boolean[] done = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!done[i]) {
                    dfs(i, visited, done);
                }
            }
            System.out.println(n - result);
        }
    }

    private static void dfs(int x, boolean[] visited, boolean[] done) {
        if (visited[x]) {
            done[x] = true;
            result++;
        } else {
            visited[x] = true;
        }

        if (!done[parent[x]]) {
            dfs(parent[x], visited, done);
        }

        visited[x] = false;
        done[x] = true;
    }
}