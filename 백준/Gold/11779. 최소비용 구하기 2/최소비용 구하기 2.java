import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Process{
		int total;
		List<Integer> cities;
		
		public Process(int total, List<Integer> cities) {
			this.total = total;
			this.cities = cities;
		}
	}

	private static int n,m,tar_from,tar_to;
	private static List<List<int[]>> list = new ArrayList<>();
	private static Process[] process;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		for(int i=0;i<=n;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new int[]{to,weight});
		}
		st = new StringTokenizer(br.readLine());
		tar_from = Integer.parseInt(st.nextToken());
		tar_to = Integer.parseInt(st.nextToken());
		
		//최적 경로를 저장할 process 배열 초기화
		process = new Process[n+1];
		for(int i=1;i<=n;i++) {
			process[i] = new Process(Integer.MAX_VALUE,new ArrayList<>());
		}
		
		travel();
		
		Process result = process[tar_to];
		System.out.println(result.total);
		System.out.println(result.cities.size());
		for(int x : result.cities) {
			System.out.print(x+" ");
		}
	}
	
	private static void travel() {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});	//노드와 거리 저장
		
		queue.offer(new int[] {tar_from,0});
		process[tar_from].total = 0;
		process[tar_from].cities.add(tar_from);
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			if(poll[0]==tar_to)	return;
			
			for(int i=0;i<list.get(poll[0]).size();i++) {
				int[] city = list.get(poll[0]).get(i);
				
				if(process[city[0]].total > process[poll[0]].total + city[1]) {
					process[city[0]].total = process[poll[0]].total + city[1];
					process[city[0]].cities = new ArrayList<>(process[poll[0]].cities);
					process[city[0]].cities.add(city[0]);
					queue.add(new int[] {city[0],process[city[0]].total});
				}
			}
		}
	}
}
