import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		int remain = 0;
		for(int i=0;i<n;i++) {
			visited = new boolean[n][m];
			int cnt = findOutline();
			if(cnt>0) {
				remain = cnt;
				time++;
			}
		}
		
		System.out.println(time);
		System.out.println(remain);
	}
	
	private static int findOutline() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0});
		
		int cnt=0;
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			visited[poll[0]][poll[1]] = true;
			
			for(int i=0;i<4;i++) {
				int nx = poll[0]+dx[i];
				int ny = poll[1]+dy[i];
				
				if(validArrange(nx, ny)) {
					visited[nx][ny] = true;
					if(board[nx][ny]==1) {	//가장 바깥쪽 치즈
						board[nx][ny]=0;
						cnt++;
					}else {
						queue.offer(new int[] {nx,ny});
					}
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean validArrange(int nx,int ny) {
		return nx>=0 && ny>=0 && nx<n && ny<m && !visited[nx][ny];
	}
}
