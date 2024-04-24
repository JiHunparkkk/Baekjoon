import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //현재의 구간 합은 이전의 구간합과 현재의 값을 더한 값을 비교
        //dp[i] = Math.max(dp[i - 1] + arr[i], arr[i])
        int min = Arrays.stream(arr).min().getAsInt();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, min);
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        int answer = Arrays.stream(dp).max().getAsInt();
        System.out.println(answer);
    }
}