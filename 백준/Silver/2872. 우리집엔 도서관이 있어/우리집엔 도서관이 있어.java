import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int maxIdx = 0, max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }

        int cnt = 1;
        for (int i = maxIdx - 1; i >= 0; i--) {
            if (arr[i] == max - 1) {
                cnt++;
                max = arr[i];
            }
        }

        System.out.println(n - cnt);
    }
}