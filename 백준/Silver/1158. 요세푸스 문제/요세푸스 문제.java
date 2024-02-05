import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n,k;
		n = sc.nextInt();
		k = sc.nextInt();
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1;i<=n;i++) {
			queue.offer(i);
		}
		
		sb.append("<");
		while(!queue.isEmpty()) {
			for(int i=0;i<k-1;i++) {
				queue.offer(queue.poll());
			}
			if(queue.size()==1) {
				break;
			}
			sb.append(queue.poll()).append(", ");
		}
		sb.append(queue.poll()).append(">");

		System.out.println(sb);
	}
}
