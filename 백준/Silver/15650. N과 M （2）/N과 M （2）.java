import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());	
		arr = new int[m];
        
		solution(0, 1);
		
		System.out.println(sb);
		br.close();
	}
	
	private static void solution(int cnt,int start) {
		if(cnt==m) {
			for(int x : arr) {
				sb.append(x+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<=n;i++) {
			arr[cnt] = i;
			solution(cnt+1, i+1);
		}
	}
}
