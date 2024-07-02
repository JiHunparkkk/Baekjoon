import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int[] difficult = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            difficult[i] = Integer.parseInt(st.nextToken());
        }

        difficult = initDifficult(n, difficult);
       
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            System.out.println(calcDifficult(x, y, difficult));
        }
    }

    private static int[] initDifficult(int n, int[] difficult) {
        int[] sum = new int[n + 1];
        for (int i = 1; i < n; i++) {
            if(difficult[i] > difficult[i + 1]) {
                sum[i] += sum[i - 1] + 1;
            } else {
                sum[i] = sum[i - 1];
            }
        }

        return sum;
    }

    private static int calcDifficult(int x, int y, int[] difficult) {
        return difficult[y - 1] - difficult[x - 1];
    }
}