import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] board = new int[101][101];
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	private static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int j=x;j<x+10;j++) {
				for(int k=y;k<y+10;k++) {
					board[j][k] = 1;
				}
			}
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(board[i][j]==1) {
					dfs(i, j);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int x, int y) {
		for(int i=0;i<4;i++) {
			int nx = x+ dx[i];
			int ny = y+ dy[i];
			
			if(nx>=0 && ny>=0 && nx<100 && ny<100 && board[nx][ny]==1) {
				board[nx][ny]=0;
				answer++;
				dfs(nx, ny);
			}
		}
	}
}
