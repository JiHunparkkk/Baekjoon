import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String input = br.readLine();
		
		int answer = 10;
		char prev = input.charAt(0);
		for(int j=1;j<input.length();j++) {
			if(prev==input.charAt(j)) {
				answer+=5;
			}else {
				answer+=10;
			}
			prev = input.charAt(j);
		}
		
		System.out.println(answer);
	
		br.close();
	}

}
