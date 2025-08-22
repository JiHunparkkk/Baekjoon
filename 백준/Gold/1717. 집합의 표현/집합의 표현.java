import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            arr[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(order == 0) {
                union(a, b);
            } else {
                if(find(a) != find(b)) {
                    sb.append("no").append("\n");
                } else {
                    sb.append("yes").append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    private static void union(int a, int b) {
        if(arr[a] == arr[b]) return;
        int pa = find(a);
        int pb = find(b);
        if(pa != pb)
            arr[pb] = pa;
    }

    private static int find(int x) {
        if(x == arr[x]) return x;
        return arr[x] = find(arr[x]);
    }
}