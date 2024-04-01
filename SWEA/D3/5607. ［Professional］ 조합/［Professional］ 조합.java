import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int P = 1234567891;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N, R;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			long answer = nCr(N, R);
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private static Long nCr (int n, int r) {
		if(r == 0) {
			return 1L;
		}
		
		long[] fac = new long[n + 1];
		fac[0] = 1;
		
		//1! 부터 n!까지 미리 계산하여 시간 단축
		for(int i = 1; i <= n; i++) {
			fac[i] = fac[i - 1] * i % P;
		}
		
		//nCr을 p로 나눈 나머지 == n!((n-r)!r!)^(p-2) % p
		return (fac[n] * power(fac[r] * fac[n - r], P - 2) % P) % P;
	}
	
	private static Long power(long x, long y) {
		long res = 1L;
		x = x % P;
		
		while(y > 0) {
			if(y % 2 == 1) {
				res = (res * x) % P;
			}
			
			y = y >> 1;
			x = (x * x) % P;
		}
		return res;
	}
}