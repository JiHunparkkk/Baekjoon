import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	private static int m,n,k;
	private static int[][] board;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=t;test_case++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			board = new int[n][m];
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				board[y][x] = 1;
			}
			
			int answer = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(board[i][j]==1) {
						dfs(i,j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	private static void dfs(int x,int y) {
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>=0 && ny>=0 && nx<n && ny<m && board[nx][ny]==1) {
				board[nx][ny]=0;
				dfs(nx, ny);
			}
		}
	}
}
