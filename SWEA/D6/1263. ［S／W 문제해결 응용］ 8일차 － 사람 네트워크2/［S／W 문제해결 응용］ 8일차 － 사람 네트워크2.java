import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int MAX = 10_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int[][] board = new int[n][n];
			
			for(int i = 0; i < n ; i++) {
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(i!=j && board[i][j] == 0) {
						board[i][j] = MAX;
					}
				}
			}
			
			int answer = solution(n, board);
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static int solution(int n, int[][] board) {
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = 0; j < n; j++) {
				if(i!=j && board[j][i] != MAX) {
					sum += board[j][i];					
				}
			}
			result = Math.min(result, sum);
		}
		return result;
	}
}