import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		int mod = 1000000;
		int period = mod / 10 * 15;

		long[] arr = new long[period + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int i = 2; i <= period; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2]) % mod;
		}

		long answer = arr[(int) (n % period)] % mod;
		System.out.println(answer);
	}
}