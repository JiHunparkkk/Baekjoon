import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=1;i<=t;i++) {
			int n = Integer.parseInt(br.readLine());
			
			int prev = 0;
			int now = 1;
			int next = 0;
			for(int j=0;j<n-1;j++) {
				next = now + prev;
				prev = now;
				now = next;
			}
			
			if(n==0) {
				System.out.println("1 0");
				continue;
			}
			System.out.println(prev+" "+now);
		}
	}
}
