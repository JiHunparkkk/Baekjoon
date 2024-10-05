import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solution() {
        long[] sum = new long[N * (N + 1) / 2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(sum);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int target = arr[j] - arr[i];
                if (Arrays.binarySearch(sum, target) > -1) {
                    answer = Math.max(answer, arr[j]);
                }
            }
        }

        System.out.println(answer);
    }
}