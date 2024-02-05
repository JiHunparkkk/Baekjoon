import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, r, c, answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solution(0, 0, (int) Math.pow(2, n));
    }

    private static void solution(int R, int C, int size) {
        if (size == 1) {
            System.out.println(answer);
            return;
        }
        int newSize = size / 2;
        if (r < R + newSize && c < C + newSize) {
            solution(R, C, newSize);
        } else if (r < R + newSize && c >= C + newSize) {
            answer += size * size / 4;
            solution(R, C + newSize, newSize);
        } else if (r >= R + newSize && c < C + newSize) {
            answer += size * size / 4 * 2;
            solution(R + newSize, C, newSize);
        } else {
            answer += size * size / 4 * 3;
            solution(R + newSize, C + newSize, newSize);
        }
    }
}