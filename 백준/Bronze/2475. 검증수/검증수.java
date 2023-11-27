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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int sum = 0;
        while (st.hasMoreTokens()) {
            sum += Math.pow(Double.parseDouble(st.nextToken()), 2);
        }
        bw.write(String.valueOf(sum % 10));

        br.close();
        bw.close();
    }
}