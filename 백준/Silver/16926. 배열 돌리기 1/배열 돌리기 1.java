import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	private static int n,m,r;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dx = {1,0,-1,0};
	private static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		board = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int j=0;j<r;j++) {
			visited = new boolean[n][m];
			for(int i=0;i<n/2;i++) {
				int k =i;
				if(k>=m) k = m-1;
				rotate(i, i,-1,-1,board[i][k]);
			}
		}
		
		
		for(int x[]:board) {
			for(int y : x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
	}
	
	private static void rotate(int x,int y,int ndx,int ndy,int prev) {
		int nx = x+ndx;
		int ny = y+ndy;
		
		if(isValid(x,y,nx, ny)) {
			visited[nx][ny] = true;
			int tmp = board[nx][ny];
			board[nx][ny] = prev;
			rotate(nx, ny, ndx, ndy,tmp);
			return;
		}
		
		for(int i=0;i<4;i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(isValid(x,y,nx, ny)) {
				visited[nx][ny] = true;
				int tmp = board[nx][ny];
				board[nx][ny] = prev;
				rotate(nx, ny,dx[i],dy[i],tmp);
				return;
			}
		}
		
	}
	
	private static boolean isValid(int x,int y,int nx,int ny) {
		return nx>=0 && ny>=0 && nx<n && ny<m && !visited[nx][ny];
	}
}
