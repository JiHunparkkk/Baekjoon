import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m, b;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int time = Integer.MAX_VALUE;
        int height = Integer.MIN_VALUE;

        for (int i = min; i <= max; i++) {
            int cntTime = 0;
            int inventory = b;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (i == arr[j][k]) {
                        continue;
                    }
                    if (i < arr[j][k]) {
                        cntTime += 2 * (arr[j][k] - i);
                        inventory += arr[j][k] - i;
                    } else {
                        cntTime += i - arr[j][k];
                        inventory -= i - arr[j][k];
                    }
                }
            }

            if (inventory < 0) {
                continue;
            }
            if (time >= cntTime) {
                time = cntTime;
                height = Math.max(height, i);
            }
        }

        System.out.println(time + " " + height);
    }

}