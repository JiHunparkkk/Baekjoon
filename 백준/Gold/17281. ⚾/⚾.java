import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N,answer;
	private static int[][] board;	//각 타자의 타율
	private static int[] sequence;//타순
	private static boolean[] visited;//타순
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][9];
		sequence = new int[9];
		visited = new boolean[9];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sequence[3] = 0;
		findSequence(0);
		System.out.println(answer);
	}
	
	private static void findSequence(int depth) {
		if(depth==9) {
			play();
			return;
		}
		
		for(int i=1;i<9;i++) {
			if(depth==3) {
				depth++;
			}
			if(!visited[i]) {
				sequence[depth] = i;
				visited[i] = true;
				findSequence(depth+1);
				visited[i] = false;
			}
		}
	}
	
	private static void play() {
		int now,out,result;
		int[] base = new int[3];
		now=out=result=0;
		
		for(int i=0;i<N;i++) {	//이닝수
			out=0;
			base = new int[3];
			while(out!=3) {
				int score = board[i][sequence[now]];
				now = (now+1)%9;
				
				if(score==0) out++;
				for(int j=0;j<score;j++) {
					result += base[2];
					for(int k=2;k>0;k--) {
						base[k] = base[k-1];
					}
					if(j==0) {
						base[0] = 1;						
					}else {
						base[0] = 0;
					}
				}
			}
		}
		answer = Math.max(answer, result);
	}
}
