import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1 ; test_case<=t ; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int n,m;
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			//nCm
			System.out.println(solution(n, m));
		}
	}
	
	private static int solution(int n, int m) {
		int[][] board = new int[m+1][n+1];
		
		for(int i=0;i<=m;i++) {
			for(int j=0,end = Math.min(i, n) ; j<=end;j++) {
				if(i==j || j==0) {
					board[i][j] = 1;
				}else {
					board[i][j] = board[i-1][j-1] + board[i-1][j];
				}
			}
		}
		
		return board[m][n];
	}
}
