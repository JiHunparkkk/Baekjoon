import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static final long MOD = 1_000_000_000L;
    private static Map<Long, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long a = Long.parseLong(st.nextToken());
        Long b = Long.parseLong(st.nextToken());

        // f(a to b) = f(b + 2) - f(a + 1)
        // f(2n) = f(2n / 2)^2 - f(2n / 2 + 1)^2
        // f(2n + 1) = f(2n/2 + 1)^2 + f(2n/2 - 1)^2
        map = new HashMap<>();
        map.put(0L, 0L);
        map.put(1L, 1L);

        long end = calculate(b + 2);
        long start = calculate(a + 1);

        System.out.println((end - start + MOD) % MOD);
    }

    private static long calculate(long n) {
        if (n <= 0) {
            return 0;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        long result;
        if (n % 2 != 0) { // 홀수
            Long a = calculate((n + 1) / 2) % MOD;
            Long b = calculate((n - 1) / 2) % MOD;
            result = (a * a + b * b) % MOD;
        } else { // 짝수
            Long a = calculate((n / 2 - 1)) % MOD;
            Long b = calculate(n / 2) % MOD;
            result = ((2 * a) + b) * b % MOD;
        }
        map.put(n, result);
        return result;
    }
}