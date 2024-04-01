import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[] dis = new int[n];
			int max = 0;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				dis[i] = Math.abs(x) + Math.abs(y);
				max = Math.max(max, dis[i]);
			}
			
			int answer = solution(n, max, dis);
			System.out.println("#" + test_case + " " + answer);
		}
		
	}
	
	private static int solution(int n, int max, int[] dis) {	
		//홀, 짝이면 성립 불가
		for(int i = 1; i < n; i++) {
			if(dis[0] % 2 != dis[i] % 2) {
				return -1;
			}
		}
		
		for(int i = 0, time = 0; ; i++) {
			time += i;
			
			if(time >= max && time % 2 == max % 2) {
				return i;
			}
		}
	}
}