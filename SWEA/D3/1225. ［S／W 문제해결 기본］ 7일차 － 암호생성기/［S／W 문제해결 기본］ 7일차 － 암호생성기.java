import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int T;
			Queue<Integer> queue = new ArrayDeque<>();
			
			T=sc.nextInt();
			for(int i=0;i<8;i++) {
				queue.offer(sc.nextInt());
			}
			
			int cnt=0,num;
			do {
				cnt+=1;
				num = queue.poll() - cnt;
				
				if(cnt==5) {
					cnt%=5;
				}
				if(num<=0) {
					num=0;
				}
				queue.offer(num);
			}while(num!=0);
			
			System.out.print("#"+T);
			while(!queue.isEmpty()) {
				System.out.print(" "+queue.poll());
			}
			System.out.println();
		}
	}
}
