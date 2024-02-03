import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, w, l, answer = 0;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[] truck = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int now = 0, j = 0;
        for (int i = 0; i < w; i++) {
            if (j < n && now + truck[j] <= l) {
                now += truck[j];
                queue.offer(truck[j++]);
            } else {
                queue.offer(0);
            }
            answer++;
        }
        
        for (int i = j; i < n; ) {
            now -= queue.poll();

            if (now + truck[i] <= l) {
                now += truck[i];
                queue.offer(truck[i++]);
            } else {
                queue.offer(0);
            }
            answer++;
        }

        int size = queue.size();
        for (int i = 1, prev = 0; i <= size; i++) {
            int poll = queue.poll();

            if (poll != 0) {
                answer += i - prev;
                prev = i;
            }
        }

        System.out.println(answer);
    }
}