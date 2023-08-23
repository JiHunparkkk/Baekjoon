import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    static int n, k, answer = 0;
    static int[] time;
    static int[] move = {-1, 1, 2};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        time = new int[100001];
        BFS();
        System.out.println(time[k]);

        System.out.println(answer);
    }

    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        time[n] = 0;

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if (tmp==k) {
                answer++;
                continue;
            }
            for (int i = 0; i < 3; i++) {
                int moving;
                if (i == 2)
                    moving = move[i] * tmp;
                else
                    moving = move[i] + tmp;

                if (moving >= 0 && moving <= 100000 && (time[moving] == 0 || time[moving] == time[tmp] + 1)) {
                    time[moving] = time[tmp] + 1;
                    queue.offer(moving);
                }
            }
        }

//        while (!queue.isEmpty()) {
//            int tmp = queue.poll();
//            if (tmp == k) {
//                answer++;
//            }
//        }
    }

}
