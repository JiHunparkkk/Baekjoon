import java.util.Scanner;

class Solution {

    public static int[] parent;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n, m, answer = 0;
            n = sc.nextInt();
            m = sc.nextInt();

            parent = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                union(a, b);
            }

            for (int i = 1; i < n + 1; i++) {
                if (i == parent[i]) {
                    answer++;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return find(parent[x]);
    }
}