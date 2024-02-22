import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	private static List<List<Integer>> phone;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case<=10;test_case++) {
			int n = sc.nextInt();
			int start = sc.nextInt();
			phone= new ArrayList<>();
			
			for(int i=0;i<=100;i++) {
				phone.add(new ArrayList<>());
			}
			
			for(int i=0;i<n/2;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				phone.get(from).add(to);
			}
			
			visited = new boolean[101];
			int answer = bfs(start);
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		
		int answer = start;
		while(!queue.isEmpty()) {
			int result = Integer.MIN_VALUE;
			int size = queue.size();
			for(int j=0;j<size;j++) {
				int poll = queue.poll();
				result = Math.max(result, poll);
				
				for(int i=0;i<phone.get(poll).size();i++){
					int num = phone.get(poll).get(i);
					
					if(!visited[num]) {
						visited[num] = true;
						queue.add(num);
					}
				}
			}
			answer = result;
		}
		
		return answer;
	}
}
