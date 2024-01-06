import java.util.ArrayList;
import java.util.Scanner;

class Solution {

    public static ArrayList<Integer>[] list;
    public static boolean[] visited;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n, m, answer = 0;
            n = sc.nextInt();
            m = sc.nextInt();

            list = new ArrayList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                list[a].add(b);
                list[b].add(a);
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }
                dfs(i);
                answer++;
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    public static void dfs(int x) {
        visited[x] = true;

        for (Integer num : list[x]) {
            if (visited[num]) {
                continue;
            }
            dfs(num);
        }
    }
}