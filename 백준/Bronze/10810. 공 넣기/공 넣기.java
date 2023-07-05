import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n, m, i, j, k;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] x = new int[n];
        for (int z = 0; z < m; z++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            for (int b = i - 1; b < j; b++) {
                x[b] = k;
            }
        }

        for(int b : x){
            System.out.print(b+" ");
        }
    }
}
