import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		String input1 = br.readLine();
		String input2 = br.readLine();
		int len1 = input1.length();
		int len2 = input2.length();
		
		int[][] dp = new int[input1.length() + 1][input2.length() + 1];
		
		//두 문자열이 같으면? 왼쪽 대각선 위 + 1
		//두 문자열이 다르면? 위, 왼쪽 중 큰 값
		int cnt = 0;
		for(int i = 1; i <= len1; i++) {
			for(int j = 1; j <= len2; j++) {
				if(input1.charAt(i - 1) == input2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
				
				cnt = Math.max(cnt, dp[i][j]);
			}
		}
		
		System.out.println(dp[len1][len2]);
		if(cnt != 0) {
			System.out.println(findSubsequence(dp, len1, len2, input1));
		}
	}
	
	private static String findSubsequence(int[][] dp, int idx1, int idx2, String input) {
		Stack<Character> stack = new Stack<>();

		while(idx1 > 0 && idx2 > 0) {
			if(dp[idx1][idx2] == dp[idx1 - 1][idx2]) {
				idx1--;
			} else if(dp[idx1][idx2] == dp[idx1][idx2 - 1]) {
				idx2--;
			} else {
				stack.push(input.charAt(idx1 - 1));
				idx1--;
				idx2--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}