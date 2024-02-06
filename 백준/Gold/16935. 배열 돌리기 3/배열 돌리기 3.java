import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int n,m,r;
	private static int[][] board;

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
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<r;i++) {
			solution(Integer.parseInt(st.nextToken()));
		}
		
		for(int[] b : board) {
			for(int x : b) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}
	
	private static void solution(int op) {
		switch (op) {
		case 1:
			upDown();
			break;
		case 2:
			leftRight();
			break;
		case 3:
			rotateR();
			break;
		case 4:
			rotateL();
			break;
		case 5:
			groupR();
			break;
		case 6:
			groupL();
			break;
		}
	}
	
	private static void upDown() {
		n  =board.length;
		m = board[0].length;
		int len = board.length;
		for(int i = 0;i<len/2;i++) {
			int[] tmp = board[i];
			board[i] = board[n-i-1];
			board[n-i-1] = tmp;
		}
	}
	
	private static void leftRight() {
		n  =board.length;
		m = board[0].length;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length/2;j++) {
				int tmp = board[i][j];
				board[i][j] = board[i][m-j-1];
				board[i][m-j-1] = tmp;
			}
		}
	}
	
	private static void rotateR() {
		int x = board.length;
		int y = board[0].length;
		int[][] newBoard = new int[y][x];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				newBoard[j][x-i-1] = board[i][j];
			}
		}
	
		board = newBoard;
	}
	
	private static void rotateL() {
		int x = board.length;
		int y = board[0].length;
		int[][] newBoard = new int[y][x];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				newBoard[y-j-1][i] = board[i][j];
			}
		}
		board = newBoard;
	}
	
	private static void groupR() {
		int x = board.length;
		int y = board[0].length;
		int[][] newBoard = new int[x][y];
		
		//1->2
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				newBoard[i][j+y/2] = board[i][j];
			}
		}
		
		//2->3
		for(int i=0;i<x/2;i++) {
			for(int j=y/2;j<y;j++) {
				newBoard[i+x/2][j] = board[i][j];
			}
		}
		
		//3->4
		for(int i=x/2;i<x;i++) {
			for(int j=y/2;j<y;j++){
				newBoard[i][j-y/2] = board[i][j];
			}
		}
		
		//4->1
		for(int i=x/2;i<x;i++) {
			for(int j=0;j<y/2;j++) {
				newBoard[i-x/2][j] = board[i][j];
			}
		}
		board = newBoard;
	}
	
	private static void groupL() {
		int x = board.length;
		int y = board[0].length;
		int[][] newBoard = new int[x][y];
		
		//1->4
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				newBoard[i+x/2][j] = board[i][j];
			}
		}
		
		//4->3
		for(int i=x/2;i<x;i++) {
			for(int j=0;j<y/2;j++) {
				newBoard[i][j+y/2] = board[i][j];
			}
		}
		
		//3->2
		for(int i=x/2;i<x;i++) {
			for(int j=y/2;j<y;j++) {
				newBoard[i-x/2][j] = board[i][j];
			}
		}
		
		//2->1
		for(int i=0;i<x/2;i++) {
			for(int j=y/2;j<y;j++) {
				newBoard[i][j-y/2] = board[i][j];
			}
		}
		board = newBoard;

	}
}
