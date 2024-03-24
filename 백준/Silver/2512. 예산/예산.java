import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int sum = 0, max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(arr[i], max);
        }

        int m = Integer.parseInt(br.readLine());

        int answer = 0;
        if (sum <= m) {
            answer = max;
        } else {
            answer = findHigh(n, max, m, arr);
        }
        System.out.println(answer);
    }

    private static int findHigh(int n, int max, int m, int[] arr) {
        int left = 1, right = max;
        while (left < right - 1) {
            int mid = (left + right) / 2;

            int sum = check(mid, n, arr);
            if (sum <= m) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int check(int h, int n, int[] arr) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.min(arr[i], h);
        }
        return sum;
    }
}