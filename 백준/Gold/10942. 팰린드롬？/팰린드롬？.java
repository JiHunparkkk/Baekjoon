import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		 
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = makeDp(n, nums);
		
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int result = 0;
			
			if(dp[from][to]) {
				result = 1;
			}		
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	private static boolean[][] makeDp(int n, int[] nums) {
		boolean[][] dp = new boolean[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			//1. 1개인 경우, 무조건 펠린드롬
			dp[i][i] = true;
			
			//2. 2개인 경우, 두 개만 비교
			if(i + 1 <= n) {
				dp[i][i + 1] = (nums[i] == nums[i + 1]);				
			}
		}
		
		//3. 3개 이상인 경우, 양 끝과 그 사이 값이 펠린드롬인지 확인
		for(int diff = 2; diff <= n; diff++) {
			for(int i = 1; i <= n; i++) {
				if(i + diff > n) {
					continue;
				}
				dp[i][i + diff] = nums[i] == nums[i + diff] && dp[i + 1][i + diff - 1];
			}
		}
		
		
		return dp;
	}
}