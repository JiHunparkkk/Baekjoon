import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,answer;
	private static int[][] arr;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		visited = new boolean[n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MAX_VALUE;
		solution(0);
		
		System.out.println(answer);
	}
	
	private static void solution(int cnt) {
		if(cnt==n) {
			int a=1,b=0;
			for(int i=0;i<n;i++) {
				if(visited[i]) {
					a*=arr[i][0];
					b+=arr[i][1];
					answer = Math.min(answer, Math.abs(a-b));
				}
			}
			return;
		}

		visited[cnt] = true;
		solution(cnt+1);
		visited[cnt] = false;
		solution(cnt+1);
		
		
	}
}
