import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Point[] points = new Point[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        bw.write(Point.ccw(points) + "\n");
        br.close();
        bw.close();
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private static int ccw(Point[] points) {
            int a = points[0].x * points[1].y + points[1].x * points[2].y + points[2].x * points[0].y;
            int b = points[1].x * points[0].y + points[2].x * points[1].y + points[0].x * points[2].y;

            int result = a - b;

            return Integer.compare(result, 0);
        }
    }
}
