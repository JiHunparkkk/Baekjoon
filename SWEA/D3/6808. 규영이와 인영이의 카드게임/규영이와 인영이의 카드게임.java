import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	private static List<Integer> a;
	private static List<Integer> b;
	private static boolean[] visited;
	private static int winAns,loseAns;
	private static int[] tmp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			a = new ArrayList<>();
			b = new ArrayList<>();
			visited = new boolean[9];
			
			for(int i=0;i<9;i++) {
				a.add(sc.nextInt());
			}
			
			for(int i=1;i<=18 ;i++) {
				if(a.contains(i)) continue;
				b.add(i);
			}
			
			winAns = 0;
			loseAns = 0;
			tmp = new int[9];
			solution(0);
			
			System.out.println("#"+test_case+" "+winAns+" "+loseAns);
		}
	}
	
	private static void solution(int d) {
		if(d==9) {
			check();
			return;
		}
		
		for(int i=0;i<9;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			tmp[d] = b.get(i);
			solution(d+1);
			visited[i] = false;
		}
	}
	
	private static void check() {
		int a_sum=0,b_sum=0;
		for(int i=0;i<9;i++) {
			if(a.get(i)>tmp[i]) {
				a_sum+=a.get(i)+tmp[i];
			}else {
				b_sum+=a.get(i)+tmp[i];
			}
		}
		if(a_sum>b_sum) winAns++;
		if(b_sum>a_sum) loseAns++;
	}
}
