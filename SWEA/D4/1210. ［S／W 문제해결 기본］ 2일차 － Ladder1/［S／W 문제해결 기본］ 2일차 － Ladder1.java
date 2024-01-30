import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Solution{
	
	private static int[][] board;
	private static int[] dx = {0,0,-1};
	private static int[] dy = {-1,1,0};
	private static int answer;
	
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			T=sc.nextInt();
			
			board = new int[100][100];
			for(int i=0; i<100;i++) {
				for(int j=0;j<100;j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0; i<100;i++) {
				if(board[99][i]==2) {
					board[99][i] = 0;
					dfs(99, i);
					break;
				}
			}
			
			System.out.println("#"+T+" "+answer);
		}
	}
	
	private static void dfs(int x,int y) {
		if(x==0) {
			answer = y;
			return;
		}
		
		boolean flag = true;
		for(int i=0;i<3;i++) {
			if(flag) {
				int nx = x+dx[i];
				int ny = y+dy[i];
			
				if(nx>=0 && ny>=0 && nx<100 && ny<100 && board[nx][ny]==1) {
					board[nx][ny] = 0;
					flag = false;
					dfs(nx, ny);
				}
			}
		}
	}
}