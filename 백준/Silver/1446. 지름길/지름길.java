import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Edge implements Comparable<Edge>{
		int from, to, dis;
		
		public Edge(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Edge o1) {
			if(this.from == o1.from) {
				return Integer.compare(this.dis, o1.dis);
			}
			return Integer.compare(this.from, o1.from);
		}
	}
	
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, d;
		st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from, to, dis;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			dis = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(from, to, dis));
		}
		
		int answer = solution(pq, d);
		System.out.println(answer);
	}
	
	private static int solution(PriorityQueue<Edge> pq, int dis) {
		int[] distance = new int[dis + 1];
		
		for(int i = 0; i <= dis; i++) {
			distance[i] = i;
		}
		
		for(int i = 0; i < dis; i++) {
			if(!pq.isEmpty() && pq.peek().from == i) {
				Edge poll = pq.poll();
				if(poll.to > dis) {
					i--;
					continue;
				}
				distance[poll.to] = Math.min(distance[poll.to], distance[i] + poll.dis);
				i--;
				continue;
			} 
			distance[i + 1] = Math.min(distance[i + 1], distance[i] + 1);
		}
		
		return distance[dis];
	}
}