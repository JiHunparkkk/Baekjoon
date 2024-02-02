import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n,k;
		long t;
		Stack<Integer> stack = new Stack();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		int[] arr= new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		for(int i=0;i<n && k>0;i++) {
			if(arr[i]<t) {
				stack.push(arr[i]);
			}else if(!stack.isEmpty()){
				int pop = stack.pop();
				
				t += pop;
				i--;
				k--;
			}
		}
		
		while(k>0 && !stack.isEmpty()) {
			k--;
			t+=stack.pop();
		}
		
		System.out.println(t);
	}

}
