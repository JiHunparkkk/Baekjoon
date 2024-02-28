import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solution(n,board));
	}
	
	private static long solution(int n,int[][] board) {
		int[][][] dp = new int[n][n][3];
		dp[0][1][0] = 1;
		
		for(int i=0;i<n;i++) {
			for(int j=2;j<n;j++) {
				if(board[i][j] == 0) {	//가로
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];					
				}
				if(i-1>=0 && board[i][j]==0) {	//세로
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				if(i-1>=0 && board[i][j]==0 && board[i][j-1]==0 && board[i-1][j]==0) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		return dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2];
	}
}
