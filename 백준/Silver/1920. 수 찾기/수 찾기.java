import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int i = 0;
        while (st.hasMoreTokens()) {
            A[i++] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int compare = Integer.parseInt(st.nextToken());

            int j;
            for (j = 0; j < n; j++) {
                if (compare == A[j]) {
                    sb.append(1 + "\n");
                    break;
                }
            }
            if (j == n) {
                sb.append(0 + "\n");
            }
        }

        bw.write(sb.toString());

        br.close();
        bw.close();

    }
}