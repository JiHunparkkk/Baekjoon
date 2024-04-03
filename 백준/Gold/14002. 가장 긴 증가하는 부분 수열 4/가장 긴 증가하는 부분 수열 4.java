import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] LIS = new int[n];
		int[] record = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = arr[0];
		record[0] = 1; // 위치 기록
		int idx = 0; // 다음 들어갈 위치
		for (int i = 1; i < n; i++) {
			if (LIS[idx] < arr[i]) {
				LIS[++idx] = arr[i];
				record[i] = idx + 1;
			} else {
				int pos = bnSearch(arr[i], 0, idx, LIS);
				LIS[pos] = arr[i];
				record[i] = pos + 1;
			}
		}

		int size = idx + 1;
		List<Integer> answer = new ArrayList<>();
		for (int i = n - 1; i >= 0; i--) {
			if (idx + 1 == record[i]) {
				idx--;
				answer.add(arr[i]);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(size).append("\n");
		for (int i = size - 1; i >= 0; i--) {
			sb.append(answer.get(i)).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int bnSearch(int num, int left, int right, int[] LIS) {
		while (left < right) {
			int mid = (left + right) / 2;

			if (LIS[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return right;
	}
}