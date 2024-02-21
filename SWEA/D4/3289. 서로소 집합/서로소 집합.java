import java.util.Scanner;

public class Solution {
	
	private static int n,m;
	private static int[] parent;
	private static StringBuilder sb;

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			n = sc.nextInt();
			m = sc.nextInt();
			parent = new int[n+1];
			sb = new StringBuilder();
			
			for(int i=1;i<=n;i++) {
				parent[i] = i;
			}
			
			for(int i=0;i<m;i++) {
				int op = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				operation(op, a, b);
			}
			
			System.out.println("#"+test_case+" "+sb);
		}
	}
	
	private static void operation(int op,int a,int b) {
		if(op==0) {
			union(a,b);
			return;
		}
		
		if(op==1) {
			int result = find(a)==find(b) ? 1 : 0;
			sb.append(result);
			return;
		}
	}
	
	private static void union(int a,int b) {
		if(find(a)==find(b)) {
			return;
		}
		
		parent[find(b)] = find(a);
	}
	
	private static int find(int x) {
		if(parent[x]==x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	
}
