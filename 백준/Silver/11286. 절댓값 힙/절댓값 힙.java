import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int x = Math.abs(o1);
				int y = Math.abs(o2);
				if(x==y) {
					return o1-o2;
				}
				return x-y;
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x==0) {
				if(queue.isEmpty()) {
					sb.append(0).append("\n");
				}else {					
					sb.append(queue.poll()).append("\n");
				}
				continue;
			}
			queue.offer(x);
		}
		
		System.out.println(sb);
	}
}
