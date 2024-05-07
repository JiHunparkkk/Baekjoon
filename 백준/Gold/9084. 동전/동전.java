import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[] coin = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int m = Integer.parseInt(br.readLine());

			int answer = solution(coin, n, m);
			System.out.println(answer);
		}
	}
	
	private static int solution(int[] coin, int n, int m) {
		int[] dp = new int[m + 1];
		dp[0] = 1; // i == coin[j] 가 0이면 같은 코인이므로 0인텍스는 1로 초기화 해줘서 계산
		
		for(int c = 0; c < n; c++) {
			for(int i = coin[c]; i <= m; i++) {
				dp[i] += dp[i - coin[c]];
			}
		}
		
		return dp[m];
	}
}