import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, X, K, A, B;
	static boolean[] cup;
	// T: tc, N: 종이컵 수, X: 왼쪽부터 몇번쨰, K: 바꾸는 수, A, B: 두 컵의 위치
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cup = new boolean[N + 1];
		cup[X] = true;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			boolean temp = cup[A];
			cup[A] = cup[B];
			cup[B] = temp;
		}
		
		for (int i = 1; i <= N; i++) {
			if(cup[i] == true) {
				System.out.println(i);
				break;
			}
		}
	}

}