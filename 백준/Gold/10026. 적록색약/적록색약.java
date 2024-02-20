import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static char[][] color;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		color = new char[n][n];
		visited = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			String input = br.readLine();
			for(int j=0;j<n;j++) {
				color[i][j] = input.charAt(j);
			}
		}
		
		int no=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(visited[i][j]==false) {
					dfs(i,j);
					no++;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(color[i][j]=='R') {
					color[i][j]='G';
				}
			}
		}
		
		int yes=0;
		visited = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(visited[i][j]==false) {
					dfs(i,j);
					yes++;
				}
			}
		}
		
		System.out.println(no+" "+yes);
		
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>=0 && ny>=0 && nx<n && ny<n && visited[nx][ny]==false) {
				if(color[x][y]==color[nx][ny]) {
					dfs(nx,ny);
				}
			}
		}
	}
	
}
