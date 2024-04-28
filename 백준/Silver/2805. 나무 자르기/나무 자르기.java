import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        //자를 높이를 이분탐색
        System.out.println(solution(0, max, arr));
    }

    private static int solution(int left, int right, int[] arr) {
        while (left <= right) {
            int mid = (left + right) / 2;
            long result = cutting(mid, arr);

            if (result >= m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (left + right) / 2;
    }

    private static long cutting(int h, int[] arr) {
        long result = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] - h > 0) {
                result += arr[i] - h;
            }
        }
        return result;
    }
}