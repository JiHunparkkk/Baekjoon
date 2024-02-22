import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n;
		n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		
		for(int i=0;i<n;i++) {
			int input = Integer.parseInt(br.readLine());
			
			if(input==0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
					continue;
				}
				sb.append(pq.poll()).append("\n");
			}else {
				pq.offer(input);
			}
		}
		
		System.out.println(sb);
	}
}
