import java.util.Scanner;

public class Solution {
	
	private static int N;
	private static long L,answer;
	private static Taste[] arr;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			N = sc.nextInt();
			L = sc.nextLong();
			arr = new Taste[N];
			visited = new boolean[N];
			
			for(int i=0;i<N;i++) {
				arr[i] = new Taste(sc.nextInt(), sc.nextInt());
			}
			answer = 0;
			solution(0);
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static void solution(int start) {
		if(start==N) {
			int cal = 0;
			int score = 0;
			for(int i=0;i<N;i++) {
				if(visited[i]) {
					cal+=arr[i].cal;
					score +=arr[i].score;
				}
			}
			if(cal<=L) {
				answer = Math.max(answer, score);
			}
			return;
		}
		
		visited[start] = true;
		solution(start+1);
		visited[start] = false;
		solution(start+1);
	}
	

}

class Taste{
	int score;
	int cal;
	
	public Taste(int score, int cal) {
		this.score = score;
		this.cal = cal;
	}
}
