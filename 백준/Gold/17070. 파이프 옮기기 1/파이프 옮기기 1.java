import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int BLOCK = 1;
	private static final int ROW = 2;
	private static final int COL = 3;
	private static final int DIAGONAL = 4;
	
	private static int n,answer;
	private static int[][] board;
	private static int[] dx = {0,1,1};	//가로,세로,대각선
	private static int[] dy = {1,0,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		board[0][1] = ROW;
		answer=0;
		
		movePipe(0, 1);
		System.out.println(answer);
	}
	
	private static void movePipe(int x,int y) {
		if(x==n-1 && y==n-1) {
			answer++;
			return;
		}
		
		int i=0;
		if(board[x][y]==COL) {
			i=1;
		}
		for(;i<3;i++) {			
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			
			if(nx>=0 && ny>=0 && nx<n && ny<n && board[nx][ny]!=BLOCK) {
				board[nx][ny] = i+2;
				
				if(board[nx][ny]==DIAGONAL) {
					boolean isBlocked = false;
					for(int j=0;j<3;j++) {
						if(board[x+dx[j]][y+dy[j]]==1) {
							isBlocked=true;
							break;
						}
					}
					if(isBlocked) {
						board[nx][ny] = 0;
						continue;
					}
				}
				
				movePipe(nx, ny);
				board[nx][ny] = 0;
			}
			if(board[x][y]==ROW) {
				i++;
			}
		}
		
	}
}
