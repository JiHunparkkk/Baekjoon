import java.awt.desktop.OpenURIHandler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static final int BLOCK = 1;
	private static final int ROW = 2;
	private static final int COL = 3;
	private static final int DIAGONAL = 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int n = Integer.parseInt(br.readLine());
		int[][] board = new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		long[][][] dp = new long[n+1][n+1][5];
		
		dp[1][2][ROW] = 1;
		for(int i=1;i<=n;i++) {
			for(int j=3;j<=n;j++) {
				if(board[i][j]==BLOCK) {
					continue;
				}
				
				//가로
				dp[i][j][ROW] = dp[i][j-1][ROW] + dp[i][j-1][DIAGONAL];
				
				//세로
				dp[i][j][COL] = dp[i-1][j][COL] + dp[i-1][j][DIAGONAL];
				
				//대각선
				if(board[i-1][j]==0 && board[i][j-1]==0) {
					dp[i][j][DIAGONAL] = dp[i-1][j-1][DIAGONAL] + dp[i-1][j-1][COL] + dp[i-1][j-1][ROW];
				}
			}
		}
		
		System.out.println(dp[n][n][COL] + dp[n][n][ROW] + dp[n][n][DIAGONAL]);
	}
}
