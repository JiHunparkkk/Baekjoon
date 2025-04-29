import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_MONEY = 10_000 * 100_000;

    private static int N, M;
    private static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        //이분탐색
        //최대금액은 10,000원
        //이분탐색하면서 최소금액 찾기
        int answer = binarySearch();
        System.out.println(answer);
    }

    private static int binarySearch() {
        int left = 1, right = MAX_MONEY;

        while(left <= right) {
            int mid = (left + right) / 2;

            long outCount = calculateCount(mid);

            if(outCount > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static long calculateCount(int minMoney) {
        int nowMoney = 0;
        long count = 0;
        for(int i = 0; i < N; i++) {
            if(money[i] > minMoney) {
                return 1000000;
            }
            if(money[i] > nowMoney) {
                count++;
                nowMoney = minMoney - money[i];
            } else {
                nowMoney -= money[i];
            }
        }

        return count;
    }

}