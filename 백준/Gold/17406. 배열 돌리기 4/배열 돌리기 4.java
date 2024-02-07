import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m,k;
	private static int r,c,s, answer = Integer.MAX_VALUE;
	private static int[][] board;
	private static int[][] newBoard;
	private static int[][] oper;
	private static boolean[] visited;
	private static int[] chooseOrder;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		oper = new int[k][3];
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			oper[i][0] = r;
			oper[i][1] = c;
			oper[i][2] = s;
		}
		
		visited = new boolean[k];
		chooseOrder = new int[k];
		
		choose(0);
		
		System.out.println(answer);
	}
	
	private static void choose(int d) {
		if(d==k) {
			copyArray();
			for(int i=0;i<k;i++) {
				int index = chooseOrder[i];
				rotate(oper[index][0], oper[index][1], oper[index][2]);
			}
			
			answer = Math.min(answer,findMinSum());
			return;
		}
		
		for(int i=0;i<k;i++) {
			if(!visited[i]) {
				visited[i] = true;
				chooseOrder[d] = i;
				choose(d+1);
				visited[i] = false;
			}
		}
	}
	
	private static void copyArray() {
		newBoard = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				newBoard[i][j] = board[i][j];
			}
		}
	}
	
	private static void rotate(int r,int c,int s) {
		int x1,y1,x2,y2;
		
		for(int i=0;i<50;i++) {	//바깥쪽 부터 안쪽 까지 차례대로 회전
		
			x1 = r-s-1+i;	//인덱스 맞추기 (0부터 시작)
			y1 = c-s-1+i;
			x2 = r+s-1-i;
			y2 = c+s-1-i;
			
			if(x1==x2 && y1==y2) break;
		
			int prev = newBoard[x1][y1];
		
			//오른쪽
			for(int j=y1+1;j<=y2;j++) {
				prev = change(x1, j, prev);
			}
			
			//아래
			for(int j=x1+1;j<=x2;j++) {
				prev = change(j, y2, prev);
			}
			
			//왼쪽
			for(int j=y2-1;j>=y1;j--) {
				prev = change(x2, j, prev);
			}
			
			//위
			for(int j=x2-1;j>=x1;j--) {
				prev = change(j, y1, prev);
			}
		}
	}
	
	private static int change(int x,int y, int num) {
		int tmp = newBoard[x][y];
		newBoard[x][y] = num;
		return tmp;
	}
	
	private static int findMinSum() {
		int result = Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++) {
			int sum = 0;
			for(int j=0;j<m;j++) {
				sum += newBoard[i][j];
			}
			result = Math.min(result, sum);
		}
		return result;
	}

}
