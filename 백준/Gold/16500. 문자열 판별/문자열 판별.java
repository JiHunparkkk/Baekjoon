import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static String S;
    private static int N;
    private static String[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        init();

        System.out.println(solution(0));
    }

    private static void init() {
        dp = new int[S.length()];
        Arrays.fill(dp, -1);
    }

    private static int solution(int pos) {
        if(pos == S.length()) return 1;

        int result = dp[pos];
        if(result != -1) return dp[pos];
        result = 0;

        for (int i = 0; i < N; i++) {
            String str = arr[i];

            if(str.length() > S.length() - pos) continue;
            if(S.substring(pos, pos + str.length()).equals(str)) {
                result += solution(pos + str.length());
            }
        }

        if(result > 0)
            result = 1;
        return dp[pos] = result;
    }
}