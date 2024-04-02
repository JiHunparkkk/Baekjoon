import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			long[] arr = new long[n + 3];
			arr[1] = 1;
			arr[2] = 1;
			arr[3] = 1;

			for (int i = 4; i <= n; i++) {
				arr[i] = arr[i - 2] + arr[i - 3];
			}

			System.out.println(arr[n]);
		}
	}
}