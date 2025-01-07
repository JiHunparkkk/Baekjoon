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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = solution(n, arr);
        System.out.println(answer);
    }

    private static int solution(int n, int[] arr) {
        //dp만을 이용하면 O(n^2)
        //이분탐색을 이용하면 O(nlogn), 이분탐색은 정렬된 값에만 정확함. 따라서 dp를 조작

        int[] dp = new int[n];
        dp[0] = arr[0];
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > dp[j]) {
                dp[++j] = arr[i];
            } else {
                //현재 arr[i] 값보다 큰 값중 제일 작은 값을 이분탐색으로 찾음
                int index = binarySearch(dp, arr[i], j);
                dp[index] = arr[i];
            }
        }

        return j + 1;
    }

    private static int binarySearch(int[] dp, int target, int length) {
        int left = 0;
        int right = length;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(dp[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}