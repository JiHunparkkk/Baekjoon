import java.util.Scanner;

public class Solution {
	
	private static int n;
	private static int[][] board;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			n =sc.nextInt();
			sc.nextLine();	// 줄바꿈 처리
			
			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				String input = sc.nextLine();
				for (int j = 0; j < n; j++) {
					board[i][j] = input.charAt(j)-'0';
				}	
			}
			
			int answer = 0;
			for(int i=0;i<n;i++) {
				for(int j=Math.abs((n/2-i)%n);j<n-Math.abs((n/2-i)%n);j++) {
					answer += board[i][j];
				}
			}
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
}
