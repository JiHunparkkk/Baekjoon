import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;

        //총 3가지 케이스로 나눠
        //1. 벌이 왼쪽 끝에 몰려있을 경우 -> 꿀통도 끝에
        long left = 0, right = 0;
        for (int i = 2; i < n; i++) {
            right += arr[i];
        }
        left = right;
        result = Math.max(result, left + right);

        for (int i = 2; i < n - 1; i++) {
            left += arr[i - 1] - arr[i];
            right -= arr[i];
            result = Math.max(result, left + right);
        }

        //2. 벌이 오른쪽 끝에 몰려있을 경우
        left = 0;
        right = 0;
        for (int i = n - 3; i >= 0; i--) {
            left += arr[i];
        }
        right = left;
        result = Math.max(result, left + right);

        for (int i = n - 3; i > 0; i--) {
            right += arr[i + 1] - arr[i];
            left -= arr[i];
            result = Math.max(result, left + right);
        }

        //3. 벌이 양쪽 끝에 있을 경우
        left = arr[1];
        right = 0;
        for (int i = 1; i <= n - 2; i++) {
            right += arr[i];
        }
        result = Math.max(result, left + right);

        //꿀통 이동
        for (int i = 2; i < n - 1; i++) {
            left += arr[i];
            right -= arr[i - 1];
            result = Math.max(result, left + right);
        }

        System.out.println(result);
    }
}