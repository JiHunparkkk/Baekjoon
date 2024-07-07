import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //구간 합을 구한다.
        //해당 구간 합 보다 크면 갯수 초기화
        int sum = 0;
        int cnt = 1;
        int lp = 0, rp;
        for (rp = 0; rp < X; rp++) {
            sum += arr[rp];
        }

        int max = sum;
        for (int i = rp; i < N; i++, lp++) {
            sum -= arr[lp];
            sum += arr[i];

            if (sum > max) {
                cnt = 1;
                max = sum;
            } else if (sum == max) {
                cnt++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }

    }
}