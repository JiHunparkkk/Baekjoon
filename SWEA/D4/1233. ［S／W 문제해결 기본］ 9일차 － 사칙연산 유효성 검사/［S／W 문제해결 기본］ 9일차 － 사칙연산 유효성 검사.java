import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			int answer = 1;			
			
			for(int i=1;i<=n;i++) {
				st = new StringTokenizer(br.readLine());
				if(st.countTokens()>2) {
					st.nextToken();
					if(!isOper(st.nextToken())) {
						answer = 0;	
					}
				}else {
					st.nextToken();
					if(isOper(st.nextToken())){
						answer = 0;
					}
				}
			}
			
			System.out.println("#"+test_case+" " + answer);
		}
	}
	
	private static boolean isOper(String op) {
		switch(op) {
		case"+":
			return true;
		case"-":
			return true;
		case"/":
			return true;
		case"*":
			return true;
		}
		return false;
	}
	
}
