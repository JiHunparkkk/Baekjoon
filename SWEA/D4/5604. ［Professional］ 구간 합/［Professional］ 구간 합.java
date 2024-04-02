import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());
			long from = Long.parseLong(st.nextToken());
			long to = Long.parseLong(st.nextToken());
			long[] num = new long[10];
			long digit = 1; // 자릿수

			if (from == 0L) {
				from = 1L;
			}
			while (from <= to) {
				// from의 첫째 자리를 0으로 조정
				while (from % 10 != 0 && from <= to) {
					calculate(num, from, digit); // from을 증가시키기 때문에 증가시키기 전 값을 미리 계산
					from++;
				}

				if (from > to) {
					break;
				}

				// to의 첫째 자리를 9로 조정
				while (to % 10 != 9 && from <= to) {
					calculate(num, to, digit);
					to--;
				}

				// 첫 째자리를 제외한 수 끼리 뺀 값에 자릿수를 곱해주면 각 숫자의 갯수를 구할 수 있다.
				long diff = to / 10 - from / 10 + 1;
				for (int i = 0; i < 10; i++) {
					num[i] += diff * digit;
				}

				digit *= 10;
				from /= 10;
				to /= 10;
			}

			long result = 0;
			for (int i = 0; i < 10; i++) {
				result += num[i] * i;
			}

			System.out.println("#" + test_case + " " + result);
		}
	}

	// 지나간 숫자 계산
	private static void calculate(long[] num, long target, long digit) {
		for (; target > 0; target /= 10) {
			num[(int) (target % 10)] += digit;
		}
	}
}