import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int s,p,answer;
	private static String str;
	private static int[] rule;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		str = br.readLine();
		rule = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			rule[i] = Integer.parseInt(st.nextToken());
		}
		
		solution();
		System.out.println(answer);
	}
	
	private static void solution() {
		int[] now = new int[5];
		
		for(int i=0;i<s;i++) {	
			now[changeIndex(str.charAt(i))]++;
			
			if(i>=p) {
				now[changeIndex(str.charAt(i-p))]--;
			}
			if(i>=p-1 && checkRule(now)) {
				answer++;
			}
		}
	}
	
	private static int changeIndex(char ch) {		
		switch(ch) {
		case'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		}
		return 4;
	}
	
	private static boolean checkRule(int[] now) {
		if(now[4]>0) return false;
		for(int i=0;i<4;i++) {
			if(now[i]<rule[i])
				return false;
		}
		return true;
	}
}
