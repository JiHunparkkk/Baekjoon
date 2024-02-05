import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Stack<Point> stack = new Stack<>();
		Stack<Point> empty = new Stack<>();
		int[] answer = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			stack.add(new Point(i,Integer.parseInt(st.nextToken())));
		}
		
		while(!stack.isEmpty()) {			
			empty.add(stack.pop());
			
			while(!empty.isEmpty() && !stack.isEmpty() && stack.peek().height < empty.peek().height) {
				empty.add(stack.pop());
			}
			while(!empty.isEmpty() && !stack.isEmpty() && stack.peek().height > empty.peek().height) {
				Point pop = empty.pop();
				answer[pop.index] = stack.peek().index+1;
			}
		}
		
		for(int x : answer) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
		
		br.close();
	}
}

class Point{
	int index;
	int height;
	
	public Point(int index, int height){
		this.index = index;
		this.height = height;
	}
}
