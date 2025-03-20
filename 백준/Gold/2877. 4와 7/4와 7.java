import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int K, count;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
    }

    private static void solution() {
        int idx = 1;
        while(true) {
            int cnt = (int)Math.pow(2, idx);

            if(K - cnt <= 0) {
                find(idx, 0, new int[idx]);
                break;
            }

            idx++;
            K -= cnt;
        }
    }

    private static void find(int limit, int depth, int[] arr) {
        if (depth == limit) {
            count++;
            if(count == K) {
                for (int i : arr) {
                    System.out.print(i);
                }
                System.out.println();
            }
            return;
        }

        arr[depth] = 4;
        find(limit, depth + 1, arr);

        arr[depth] = 7;
        find(limit, depth + 1, arr);
    }
}
