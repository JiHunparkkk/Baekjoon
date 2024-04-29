import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int n;
	private static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, board[i][j]);
			}
		}
		
		int answer = 1;
		//물에 잠기게 한다
		for(int i = 1; i <= max; i++) {
			sink(i);
			answer = Math.max(answer, findSafe(new boolean[n][n]));
		}
		
		System.out.println(answer);
	}
	
	private static void sink(int h) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] <= h) { 
					board[i][j] = 0;
				}
			}
		}
	}
	
	private static int findSafe(boolean[][] visited) {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j] && board[i][j] > 0) {
					visited[i][j] = true;
					cnt++;
					dfs(i, j, visited);
				}
			}
		}
		return cnt;
	}
	
	private static void dfs(int x, int y, boolean[][] visited) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && board[nx][ny] > 0) {
				visited[nx][ny] = true;
				dfs(nx, ny, visited);
			}
		}
	}
}