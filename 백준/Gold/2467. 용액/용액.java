import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[]{arr[0], arr[n - 1]};
        int left = 0;
        int right = n - 1;
        int result = Integer.MAX_VALUE;
        while (left < right) {
            int sum = arr[left] + arr[right];

            if(result > Math.abs(sum)) {
                result = Math.abs(sum);
                answer[0] = arr[left];
                answer[1] = arr[right];
            }

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                break;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}