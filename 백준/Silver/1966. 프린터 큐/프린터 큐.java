import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int test_case = Integer.parseInt(br.readLine());

        for (int i = 0; i < test_case; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int solution() {

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queue.offer(new Point(i, arr[i]));
        }
        Arrays.sort(arr);

        int result = 0;
        int i = n - 1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            if (poll.priority == arr[i]) {
                result++;
                if (m == poll.index) {
                    break;
                }
                i--;
            } else {
                queue.offer(poll);
            }
        }

        return result;
    }

    static class Point {
        int index;
        int priority;

        public Point(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}