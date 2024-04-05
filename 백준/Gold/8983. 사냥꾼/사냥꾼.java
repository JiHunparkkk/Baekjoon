import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (y == o.y) {
                return Integer.compare(x, o.x);
            }
            return Integer.compare(y, o.y);
        }
    }

    private static int M, N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Point[] shooter = new Point[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            shooter[i] = new Point(0, num);
        }

        PriorityQueue<Point> animal = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            animal.add(new Point(y, x));
        }

        Arrays.sort(shooter);
        int answer = 0;
        boolean notArrange = false;   //이전에도 사냥하지 못한 동물일 경우 그냥 삭제
        for (int i = 0; i < M; i++) {
            while (!animal.isEmpty()) {
                Point peek = animal.peek();
                int dis = Math.abs(shooter[i].y - peek.y) + peek.x;

                if (dis > L) {
                    if (notArrange) {   //이전 사대와 다음 사대 둘 다 사냥 못하면 삭제 후 사냥 재시작
                        animal.poll();
                        notArrange = false;
                        continue;
                    } else {
                        notArrange = true;
                    }
                    break;
                }
                animal.poll();
                answer++;
                notArrange = false;
            }
        }

        System.out.println(answer);
    }
}