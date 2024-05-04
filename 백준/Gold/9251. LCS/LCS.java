import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len2 + 1][len1 + 1];

        //하나씩 확인
        //같으면 이전 + 1 == [i-1][j-1] + 1
        //다르면 이전꺼 중 큰 값 따라감
        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <= len1; j++) {
                if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[len2][len1]);
    }
}