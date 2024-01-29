import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int BLANK = 0;
	
	private static int board[][];
	private static boolean visited[][];
	private static int cnt;
	private static int[] dx = {1,1,0,-1};
	private static int[] dy = {0,1,1,1};

	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[19][19];
		visited = new boolean[19][19];
		for(int i=0;i<19;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<19;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				if(!visited[j][i] && board[j][i]!=BLANK) {
					visited[j][i] = true;
					
					if(solution(j,i)) {
						br.close();
						return;
					}
				}
			}
		}
		
		System.out.println("0");
		br.close();
	}
	
	private static boolean solution(int x,int y) {
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			cnt=2;
			if(nx>=0 && ny>=0 && nx<19 && ny<19
					&& visited[nx][ny]!=true && board[x][y] == board[nx][ny]) {
				dfs(nx,ny,dx[i],dy[i]);
			}
			
			if(cnt==5) {
				if(!checkSix(x,y,dx[i],dy[i])) {
					System.out.println(board[x][y]);
					System.out.println((x+1)+ " "+(y+1));
					return true;
				}
			}
		}
		return false;
	}
	
	private static void dfs(int x, int y,int ddx,int ddy) {
		int nx = x+ddx;
		int ny = y+ddy;
		
		if(nx>=0 && ny>=0 && nx<19 && ny<19
				&& visited[nx][ny]!=true && board[x][y] == board[nx][ny]) {
			cnt++;
			dfs(nx,ny,ddx,ddy);
		}
	}
	
	private static boolean checkSix(int x,int y,int ddx,int ddy) {
		int nx = x-ddx;
		int ny = y-ddy;
		
		if(nx>=0 && ny>=0 && nx<19 && ny<19 && board[x][y] == board[nx][ny]) {
			return true;
		}
		return false;
	}
}

