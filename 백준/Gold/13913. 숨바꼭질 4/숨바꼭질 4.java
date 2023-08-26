import java.util.*;

public class Main {
    static int n, k, L;
    static int[] move = {-1, 1, 2};
    static int[] ch, already;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        ch = new int[100001];
        already = new int[100001];

        BFS();

        StringBuilder sb = new StringBuilder();
        sb.append(already[k]).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if (tmp == k) {
                break;
            }

            for (int i = 0; i < 3; i++) {
                int moving;
                if(i==2)
                    moving = tmp * move[i];
                else
                    moving = tmp + move[i];
                if(moving>=0 && moving<100001 && already[moving]==0){
                    ch[moving] = tmp;
                    already[moving] = already[tmp] + 1;
                    queue.offer(moving);
                }
            }
        }

        int result;
        result = k;
        while(result!=n) {
            stack.push(result);
            result = ch[result];
        }
        stack.push(result);
    }
}
