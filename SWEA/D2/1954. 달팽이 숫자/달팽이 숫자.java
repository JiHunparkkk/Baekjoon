import java.util.Scanner;

public class Solution {
	
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int[][] board;
	private static boolean[][] visited;
	private static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int test_case = 1 ; test_case<=t;test_case++) {
			n = sc.nextInt();
			
			board = new int[n][n];
			visited = new boolean[n][n];
			
			visited[0][0] = true;
			board[0][0] = 1 ;
			
			move(0, 0, dx[0],dy[0], 2);
			
			System.out.println("#"+test_case);
			for(int[] arr : board) {
				for(int num : arr) {
					System.out.print(num+" ");
				}
				System.out.println();
			}
		}
	}

	
	private static void move(int x,int y,int ddx,int ddy,int num) {
				
		for(int i=0;i<5;i++) {
			int nx = x + ddx;
			int ny = y + ddy;
			
			if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[nx][ny]) {
				visited[nx][ny] = true;
				board[nx][ny] = num;
				move(nx, ny,ddx,ddy, num+1);
				break;
			}
			if(i!=4) {				
				ddx = dx[i];
				ddy = dy[i];	
			}
		}
		
	}
}
