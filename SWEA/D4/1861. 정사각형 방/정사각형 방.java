import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static int n,maxRoom,room;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <=t ;test_case++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxRoom=0;
			int startRoom = Integer.MAX_VALUE;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					visited = new boolean[n][n];
					room = Integer.MIN_VALUE;
					visited[i][j] = true;
					dfs(i, j, 1);
					if(maxRoom<=room) {
						if(maxRoom==room) {
							startRoom = Math.min(startRoom,board[i][j]);							
						}else {
							startRoom = board[i][j];
						}
						maxRoom = room;
					}
				}
			}
			
			System.out.println("#"+test_case+" "+startRoom+" "+maxRoom);
		}
	}
	
	private static void dfs(int x,int y,int cnt) {
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[nx][ny] && board[x][y]+1==board[nx][ny]) {
				visited[nx][ny] = true;
				room = Math.max(room, cnt+1);
				dfs(nx, ny,cnt+1);
			}
		}
	}
}
