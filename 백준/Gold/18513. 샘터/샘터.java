import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, K;
    private static int[] arr;
    private static Set<Long> set;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solution() {
        Queue<Long> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.offer((long) arr[i]);
            set.add((long) arr[i]);
        }

        long cnt = 0;
        long distance = 1;
        long result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                long poll = queue.poll();
                if (!set.contains(poll + 1)) {
                    set.add(poll + 1);
                    queue.offer(poll + 1);
                    cnt++;
                    result += distance;
                }
                if (cnt >= K) {
                    break;
                }
                if (!set.contains(poll - 1)) {
                    set.add(poll - 1);
                    queue.offer(poll - 1);
                    cnt++;
                    result += distance;
                }
                if (cnt >= K) {
                    break;
                }
            }

            distance++;

            if (cnt >= K) {
                break;
            }
        }

        System.out.println(result);
    }

    private static boolean isFinish(int cnt) {
        return cnt >= K;
    }
}