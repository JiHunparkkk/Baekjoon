import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n;
        n = Integer.parseInt(br.readLine());

        int[] board = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int from, to;
        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        if (from == to) {
            System.out.println(0);
        } else{
            System.out.println(solution(n, board, from, to));
        }
    }

    private static int solution(int n, int[] board, int from, int to) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(from);

        int[] distance = new int[n + 1];
        Arrays.fill(distance, 10001);
        distance[from] = 0;

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if(poll == to) {
                return distance[poll];
            }

            //전진
            for (int i = 1; i * board[poll] + poll <= n; i++) {
                int next = board[poll] * i + poll;

                if(distance[next] > distance[poll] + 1){
                    distance[next] = distance[poll] + 1;

                    if(next == to){
                        return distance[next];
                    }
                    queue.add(next);
                }
            }

            //후진
            for (int i = 1; poll - i * board[poll] > 0; i++) {
                int next = poll - board[poll] * i;

                if (next < 1) {
                    break;
                }

                if(distance[next] > distance[poll] + 1){
                    distance[next] = distance[poll] + 1;

                    if(next == to){
                        return distance[next];
                    }
                    queue.add(next);
                }
            }
        }
        return -1;
    }
}