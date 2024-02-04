import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n, answer;
    private static int[] people;
    private static List<List<Integer>> list = new ArrayList<>();
    private static boolean[] visited;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n];
        visited = new boolean[n];
        check = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new ArrayList<>());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        answer = Integer.MAX_VALUE;
        solution(0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    private static void solution(int start) {
        int groupCnt = 0;
        int teamA = 0, teamB = 0;

        Arrays.fill(check, false);

        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                bfs(i, visited[i]);
                groupCnt++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                teamA += people[i];
            } else {
                teamB += people[i];
            }
        }

        if (groupCnt == 2) {
            answer = Math.min(answer, Math.abs(teamA - teamB));
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                solution(start + 1);
                visited[i] = false;
            }
        }
    }

    private static void bfs(int x, boolean team) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        check[x] = true;

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 0; i < list.get(poll).size(); i++) {
                int target = list.get(poll).get(i) - 1;
                if (!check[target] && visited[target] == team) {
                    check[target] = true;
                    queue.add(target);
                }
            }
        }

    }
}