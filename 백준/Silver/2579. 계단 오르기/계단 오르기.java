import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        bw.write(solution(n, arr) + "\n");
        bw.close();
        br.close();
    }

    private static int solution(int n, int[] arr) {
        int[] dy = new int[n];

        dy[0] = arr[0];
        if (n == 1) {
            return dy[0];
        }
        dy[1] = dy[0] + arr[1];
        if (n == 2) {
            return dy[1];
        }

        dy[2] = Math.max(arr[0], arr[1]) + arr[2];
        if (n == 3) {
            return dy[2];
        }

        for (int i = 3; i < n; i++) {
            dy[i] = Math.max(dy[i - 2], dy[i - 3] + arr[i - 1]) + arr[i];
        }

        return dy[n - 1];
    }
}
