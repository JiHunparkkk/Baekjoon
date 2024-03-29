import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, k;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        Integer[] diff = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff, Collections.reverseOrder());

        int answer = 0;
        for (int i = k - 1; i < diff.length; i++) {
            answer += diff[i];
        }
        System.out.println(answer);
    }
}