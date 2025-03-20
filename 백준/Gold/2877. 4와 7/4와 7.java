import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int K;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
    }

    private static void solution() {
        String result = Integer.toBinaryString(K + 1).replace('0', '4').replace('1', '7');
        for (int i = 1; i < result.length(); i++) {
            System.out.print(result.charAt(i));
        }
    }

}
