import java.util.Scanner;

public class Solution{
	
	private static int n,m,answer;
	private static int[] snack;
	private static int[] pick;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			n = sc.nextInt();
			m = sc.nextInt();
			snack = new int[n];
			pick = new int[2];
			
			for(int i=0;i<n;i++) {
				snack[i] = sc.nextInt();
			}
			
			answer = -1;
			solution(0, 0);
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static void solution(int d,int start) {
		if(d==2) {
			int result = pick[0]+pick[1];
			if(result<=m) {
				answer = Math.max(answer,result);				
			}
			return;
		}
		
		for(int i=start;i<n;i++) {
			pick[d] = snack[i];
			solution(d+1, i+1);
		}
	}
}
