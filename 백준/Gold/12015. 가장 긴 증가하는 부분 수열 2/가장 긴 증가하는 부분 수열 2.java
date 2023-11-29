import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] LIS = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = arr[0];
        int LIS_length = 1;
        for (int i = 1; i < n; i++) {
            int key = arr[i];

            if (LIS[LIS_length - 1] < key) {
                LIS[LIS_length++] = key;
            } else {
                int left = 0;
                int right = LIS_length;
                while (left < right) {
                    int mid = (left + right) >>> 1;

                    if (LIS[mid] < key) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                LIS[left] = key;
            }
        }

        System.out.println(LIS_length);
    }
}