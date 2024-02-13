import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] dp = new int[n+3];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[3] = 1;
		dp[4] = -1;
		dp[5] = 1;
		for(int i=6;i<=n;i++) {
			int min = dp[i];
			if(dp[i-3]!=-1 && min>dp[i-3]) {
				min = dp[i-3];
			}
			if(dp[i-5]!=-1 && min>dp[i-5]) {
				min = dp[i-5];
			}
			
			if(min<dp[i]) {
				dp[i] = min+1;
			}
		}
		
		if(dp[n]==Integer.MAX_VALUE) {
			dp[n] = -1;
		}
		System.out.println(dp[n]);
	}
}
