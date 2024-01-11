import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        Time[] times = new Time[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            times[i] = new Time(a, b);
        }

        Arrays.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        System.out.println(solution(n, times));
    }

    private static int solution(int n, Time[] times) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(times[0].end);

        for (int i = 1; i < n; i++) {
            if (pq.size() > 0 && pq.peek() > times[i].start) {
                pq.offer(times[i].end);
            } else {
                pq.poll();
                pq.offer(times[i].end);
            }
        }

        return pq.size();
    }
}

class Time {
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
