import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int[] visited = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, k;
        n = sc.nextInt();
        k = sc.nextInt();

        System.out.println(bfs(n, k));
    }

    private static int bfs(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        visited[n] = 0;

        while (!queue.isEmpty()) {

            int poll = queue.poll();

            if (poll == k) {
                return visited[poll];
            }

            if (poll - 1 >= 0 && visited[poll - 1] == 0) {
                visited[poll - 1] = visited[poll] + 1;
                queue.offer(poll - 1);
            }
            if (poll + 1 <= 100000 && visited[poll + 1] == 0) {
                visited[poll + 1] = visited[poll] + 1;
                queue.offer(poll + 1);
            }
            if (poll * 2 <= 100000 && visited[poll * 2] == 0) {
                visited[poll * 2] = visited[poll] + 1;
                queue.offer(poll * 2);
            }
        }

        return visited[k];
    }
}
