import java.io.*;
import java.util.*;

class Main {

    private static int n, answer;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        choice(0, new int[n], new boolean[n]);
        System.out.println(answer);
    }

    private static void choice(int depth, int[] chose, boolean[] visited) {
        if (depth == n) {
            answer = Math.max(answer, sum(chose));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                chose[depth] = i;
                visited[i] = true;
                choice(depth + 1, chose, visited);
                visited[i] = false;
            }
        }
    }

    private static int sum(int[] chose) {
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            result += Math.abs(arr[chose[i]] - arr[chose[i + 1]]);
        }
        return result;
    }
}