import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static int N, K, W;
    private static List<List<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            list = new ArrayList<>();
            list.add(new ArrayList<>());    //0 인덱스 처리

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                list.add(new ArrayList<>());
                time[i] = Integer.parseInt(st.nextToken());
            }

            int[] in_degree = new int[N + 1]; //시작점을 찾기 위한 배열
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from, to;
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());

                in_degree[to]++;
                list.get(from).add(to);
            }

            W = Integer.parseInt(br.readLine());
            int answer = solution(time, in_degree);
            System.out.println(answer);
        }
    }

    private static int solution(int[] time, int[] in_degree) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] totalTime = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (in_degree[i] == 0) {
                queue.add(i);
                totalTime[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 0; i < list.get(poll).size(); i++) {
                int next = list.get(poll).get(i);
                if (--in_degree[next] == 0) {
                    queue.add(next);
                }
                totalTime[next] = Math.max(totalTime[poll] + time[next], totalTime[next]);
            }
        }

        return totalTime[W];
    }
}