import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int x,w;

		public Node(int x, int w) {
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	private static int V,E,start;
	private static List<List<Node>> list = new ArrayList<>();
	private static int[] weight;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		weight = new int[V+1];
		visited = new boolean[V+1];
		
		Arrays.fill(weight, Integer.MAX_VALUE);
		for(int i=0;i<=V;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add(new Node(end,weight));
		}
		
		solution();
		for(int i=1;i<weight.length;i++) {
			if(weight[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(weight[i]);
		}
	}
	
	private static void solution() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		weight[start] = 0;
		
		while(!pq.isEmpty()) {
			Node poll = pq.poll();
			
			if(!visited[poll.x]) {
				visited[poll.x] = true;
			}
			
			for(int i=0;i<list.get(poll.x).size();i++) {
				Node next = list.get(poll.x).get(i);
				if(!visited[next.x] && weight[next.x] > next.w + weight[poll.x]) {
					weight[next.x] = next.w + weight[poll.x];
					pq.add(new Node(next.x,weight[next.x]));
				}
			}
		}
	}
}
