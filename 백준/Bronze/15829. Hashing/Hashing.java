import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final double R = 31;
    private static final int M = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();

        long sum = 0L;
        int i = 0;
        for (char ch : str.toCharArray()) {
            int num = ch - 'a' + 1;
            sum += num * Math.pow(R, i++) % M;
        }
        System.out.println(sum);
        br.close();
    }
}