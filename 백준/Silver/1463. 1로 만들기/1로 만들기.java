import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();		
		int[] dp = new int[n+3];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for(int i=4;i<=n;i++) {
			int min = dp[i];
			if(min>dp[i-1]) {
				min = dp[i-1];
			}
			if(i%2==0 && min>dp[i/2]) {
				min = dp[i/2];
			}
			if(i%3==0 && min>dp[i/3]) {
				min = dp[i/3];
			}
			dp[i] = min+1;
		}
		
		System.out.println(dp[n]);
	}
}