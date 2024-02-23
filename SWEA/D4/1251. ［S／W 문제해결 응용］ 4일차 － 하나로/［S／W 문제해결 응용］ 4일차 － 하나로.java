import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Vertex implements Comparable<Vertex>{
		int no;
		long weight;

		public Vertex(int no, long weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(weight, o.weight);
		}
	}
	
	private static long[][] adjMatrix;
	private static int n;
	private static double E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=test_case;tc++) {
			n = Integer.parseInt(br.readLine());
			
			int[] x = new int[n];
			int[] y = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			adjMatrix = new long[n][n];
			make(x,y);
			
			//임의의 정점 꺼내서
			//나머지 정점과의 거리 계산 및 저장
			//갱신된 제일 가까운 곳의 노드를 pq에 저장
			long[] minEdge = new long[n];
			boolean[] visited = new boolean[n];
			
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			Arrays.fill(minEdge, Long.MAX_VALUE);
			pq.add(new Vertex(0,0));
			minEdge[0] = 0L;
			
			int cnt=0;
			double total = 0;
			while(!pq.isEmpty()) {
				Vertex poll = pq.poll();
				
				if(visited[poll.no]) continue;
				
				total += (E * (poll.weight));
				visited[poll.no] = true;
				
				if(++cnt==n) break;
				

				for(int i=0;i<n;i++) {
					if(!visited[i]  && adjMatrix[poll.no][i] != 0 ) {
						if(minEdge[i]>adjMatrix[poll.no][i]) {
							minEdge[i] = adjMatrix[poll.no][i];
							pq.add(new Vertex(i,minEdge[i]));
						}
					}
				}
			}
			
			System.out.println("#"+tc+" "+(long)(total+0.5));
		}
	}
	
	private static void make(int[] nx,int[] ny) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j) {
					long weight = (long)( Math.pow(nx[i] - nx[j],2) + Math.pow(ny[i] - ny[j],2));
					adjMatrix[i][j] = weight;
				}
			}
		}
		
//		System.out.println(Arrays.deepToString(adjMatrix));
		
	}
}
