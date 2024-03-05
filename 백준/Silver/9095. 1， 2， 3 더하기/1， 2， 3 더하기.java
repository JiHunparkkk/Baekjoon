import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,answer;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1 ; test_case<=T; test_case++) {
			n = Integer.parseInt(br.readLine());
			answer = 0;
			
			solution(0);
			System.out.println(answer);
		}
	}
	
	private static void solution(int x) {
		if(x>=n) {
			if(x==n) {
				answer++;
			}
			return;
		}
		
		for(int i=1; i<=3; i++) {
			solution(x+i);
		}
	}
}