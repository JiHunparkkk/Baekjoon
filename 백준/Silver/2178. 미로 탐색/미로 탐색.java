import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int n, m;
	private static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int[][] visited = new int[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, 0});
		visited[0][0] = 1;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int x = poll[0];
			int y = poll[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny] == 0 && board[nx][ny] == 1) {
					visited[nx][ny] = visited[x][y] + 1;
					queue.add(new int[] {nx, ny});
				}
			}
		}
		
		return visited[n - 1][m - 1];
	}
}