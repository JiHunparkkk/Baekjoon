import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m;
	private static StringBuilder sb = new StringBuilder();
	private static List<List<Integer>> list = new ArrayList<>();
	private static int[] indegree;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		indegree = new int[n+1];
		
		for(int i=0;i<n+1;i++) {
			list.add(new ArrayList<>());
		}

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			indegree[b] +=1;
		}

		checkPriority();
	
		System.out.println(sb);
	}
	
	private static void checkPriority() {
		Queue<Integer> queue = new ArrayDeque<>();	
		
		for(int i=1;i<n+1;i++) {
			if(indegree[i]==0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			
			if(indegree[poll]==0) {
				sb.append(poll).append(" ");
			}
			
			for(int i=0;i<list.get(poll).size();i++) {
				int num = list.get(poll).get(i);
				if(indegree[num]>0) {
					indegree[num]-=1;
				}
				if(indegree[num]==0) {
					queue.offer(num);
				}
			}
		}
	}
}
