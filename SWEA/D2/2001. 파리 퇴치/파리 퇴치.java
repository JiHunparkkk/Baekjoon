import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n =sc.nextInt();
			int m = sc.nextInt();
			
			int[][] board = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			int answer = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					int sum=0;
					for(int k=i ; k<i+m ; k++) {
						for(int z=j ; z<j+m ; z++) {
							if(k<n && z<n)
								sum+=board[k][z];
						}
					}
					answer = Math.max(answer, sum);
				}
			}
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	

}