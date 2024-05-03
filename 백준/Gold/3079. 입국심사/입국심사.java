import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] screeningTime = new int[n];
		long max = 0;
		
		for(int i = 0; i < n; i++) {
			screeningTime[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, screeningTime[i]);
		}
		
		//총 시간을 이분 탐색 
		//각 심사대 별로 이분 탐색된 시간 미만으로 인원 받음
		//m만큼 딱 받을 수 있을 때 까지 탐색
		long result = binarySearch(1, max * m + 1, screeningTime);
		System.out.println(result);
	}
	
	private static long binarySearch(long left, long right, int[] screeningTime) {
		while(left <= right) {
			long mid = (left + right) / 2;
			long totalCnt = calculate(mid, screeningTime);
			
			if(totalCnt >= m) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
	
	private static long calculate(long maxTime, int[] screeningTime) {
		long totalCnt = 0;
		for(int i = 0; i < n; i++) {
			totalCnt += maxTime / screeningTime[i];
			if(totalCnt > m) {
				break;
			}
		}
		return totalCnt;
	}
}