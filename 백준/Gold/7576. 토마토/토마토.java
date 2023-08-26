import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int m, n, L;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean flag=true;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j]==0) flag=false;
            }
        }

        if(flag)
            System.out.println(0);
        else{
            BFS();
            System.out.println(L);
        }

    }

    static void BFS() {
        Queue<Point12> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]==1)
                    queue.offer(new Point12(i, j, 0));
            }
        }

        L = 0;
        while (!queue.isEmpty()) {
            Point12 tmp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny]==0) {
                    arr[nx][ny] = 1;
                    queue.offer(new Point12(nx, ny, tmp.time + 1));
                }
            }
            L = Math.max(tmp.time, L);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]==0) {
                    L = -1;
                    return;
                }
            }
        }
    }
}

class Point12{
    int x,y,time;

    public Point12(int x, int y,int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}