import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M, L;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        list.add(0);
        list.add(L);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void solution() {
        Collections.sort(list);

        //구간 길이는 최소 1 ~ L
        //구간 최소 길이를 찾기위해 이분탐색
        int left = 1, right = L - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (valid(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    private static boolean valid(int mid) {
        int cnt = 0;
        for (int i = 1; i < list.size(); i++) {
            cnt += (list.get(i) - list.get(i - 1) - 1) / mid;
        }
        return cnt > M;
    }
}