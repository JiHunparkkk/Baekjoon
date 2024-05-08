import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Edge{
		int to, weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		List<List<Edge>> list = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Edge(b, c));
			list.get(b).add(new Edge(a, c));
		}
		
		int s, t;
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		int answer = solution(n ,m ,s, t, list);
		System.out.println(answer);
		
	}
	
	private static int solution(int n, int m, int s, int t, List<List<Edge>> list) {
		int[] result = new int[n + 1];
		Arrays.fill(result, m * 100 + 1); //최댓값으로 초기화
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(s);
		result[s] = 0;
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			List<Edge> now = list.get(poll);
			
			for(int i = 0; i < now.size(); i++) {
				Edge next = now.get(i);
				
				if(result[next.to] > result[poll] + next.weight) {
					result[next.to] = result[poll] + next.weight;
					queue.add(next.to);
				}
			}
		}
		
		return result[t];
	}
	
}