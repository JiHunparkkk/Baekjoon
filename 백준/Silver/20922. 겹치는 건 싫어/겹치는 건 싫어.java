import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] numCnt = new int[100001];
        int max = 0;
        int length = 0;
        int lp = 0, rp;
        for (rp = 0; rp < N; rp++) {
            if (numCnt[arr[rp]] + 1 > K) {
                break;
            }
            numCnt[arr[rp]]++;
            length++;
        }

        max = length;
        for (int i = rp; i < N; i++) {
            while(numCnt[arr[i]] + 1 > K) {
                numCnt[arr[lp]]--;
                lp++;
                length--;
            }
            numCnt[arr[i]]++;
            length++;
            max = Math.max(max, length);
        }

        System.out.println(max);
    }
}