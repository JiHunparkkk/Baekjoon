import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int R = 31;
    private static final int M = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();

        long sum = 0L;
        long r = 1;
        for (char ch : str.toCharArray()) {
            int num = ch - 'a' + 1;
            sum += num * r;
            r = r * R % M;
        }
        System.out.println(sum % M);
        br.close();
    }
}