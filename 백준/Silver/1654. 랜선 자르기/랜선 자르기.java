import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static int k, n;
    private static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];
        int max = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        binarySearch(arr, 1, Integer.MAX_VALUE);
        System.out.println(result);
    }

    private static void binarySearch(int[] arr, long left, long right) {
        if (left > right) {
            return;
        }

        long mid = (right + left) / 2;
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            cnt += (arr[i] / mid);
        }

        if (cnt < n) {
            binarySearch(arr, left, mid - 1);
        } else {
            result = mid;
            binarySearch(arr, mid + 1, right);
        }
    }
}