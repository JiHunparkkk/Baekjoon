import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int from,to,weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o1) {
			return Integer.compare(this.weight, o1.weight);
		}
	}
	
	private static int V,E;
	private static Edge[] edges;
	private static int[] parents;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			V = sc.nextInt();
			E = sc.nextInt();
			
			edges = new Edge[E];
			for(int i=0;i<E;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int weight = sc.nextInt();
		
				edges[i] = new Edge(from,to,weight);
			}
			
			Arrays.sort(edges);
			make();
			
			int cnt=0;
			long weight=0;
			for(Edge edge:edges) {
				if(!union(edge)) continue;
				weight+=edge.weight;
				if(++cnt==V-1) break;
			}
			
			System.out.println("#"+test_case+" "+weight);
		}
	}
	
	private static void make() {
		parents = new int[V+1];
		
		for(int i=1;i<=V;i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(Edge edge) {
		int aRoot = find(edge.from);
		int bRoot = find(edge.to);
		
		if(aRoot==bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
}
