import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int n = sc.nextInt();
			List<Integer> origin = new LinkedList<>();
			
			for(int i=0;i<n;i++) {
				origin.add(sc.nextInt());
			}
			
			int m = sc.nextInt();
			for(int i=0;i<m;i++) {
				char insert = sc.next().charAt(0);
				int strt = sc.nextInt();
				int count = sc.nextInt();
				
				for(int j=0;j<count;j++) {
					origin.add(strt++,sc.nextInt());
				}
				
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			for(int i=0;i<10;i++) {
				sb.append(origin.get(i)).append(" ");
			}
			System.out.println(sb);
		}
		
	}
}
