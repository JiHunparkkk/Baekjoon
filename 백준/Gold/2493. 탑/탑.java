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
		Stack<Integer> stack = new Stack<>();
		int[] answer = new int[n];
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=n-1;i>0;i--) {
			int index = stack.push(i);
			
			while(!stack.isEmpty() && arr[index-1] > arr[stack.peek()]) {
				answer[stack.pop()] = index;
			}
		}
		
		for(int x : answer) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
		
		br.close();
	}
}
