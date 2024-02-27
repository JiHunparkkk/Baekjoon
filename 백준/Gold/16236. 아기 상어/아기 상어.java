import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int x, y, dis;

        public Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Point o) {
            if (dis == o.dis) {
                if (x == o.x) {
                    return y - o.y;
                }
                return x - o.x;
            }
            return dis - o.dis;
        }
    }

    static class Shark extends Point {
        int size, now;

        public Shark(int x, int y, int size, int now) {
            super(x, y, Integer.MAX_VALUE);
            this.size = size;
            this.now = now;
        }
    }

    private static int n, answer;
    private static int[][] board;
    private static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    shark = new Shark(i, j, 2, 2);
                }
            }
        }

        while (true) {
            boolean flag = bfs();
            if (!flag) {
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs() {
    	PriorityQueue<Point> pq = new PriorityQueue<>();
    	Queue<Point> queue = new ArrayDeque<>();
        queue.add(shark);

        boolean[][] visited = new boolean[n][n];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean isPossible = false;

        visited[shark.x][shark.y] = true;
        int dis = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int j = 0; j < size; j++) {
                Point poll = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] 
                    		&& board[nx][ny] <= shark.size) {
                        queue.add(new Point(nx, ny, dis));
                        if (board[nx][ny]!=0 && board[nx][ny] <shark.size) {
                            pq.add(new Point(nx, ny, dis));
                        }
                        visited[nx][ny] = true;
                        isPossible = true;
                    }
                }
            }
            dis++;
        }

        if (!isPossible || pq.isEmpty()) {    //더 이상 움직일 수 없다면 종료
            return false;
        }
        //먹을 수 있는지 이동하면서 확인
        return move(pq.poll());
    }

    private static boolean move(Point point) {
    	if(point==null) {
    		return false;
    	}
    	
        answer += point.dis;
        board[shark.x][shark.y] = 0;
        
        int now = shark.now - 1;
        int size = shark.size;
        if (now == 0) {
            size += 1;
            now = size;
        }
        
        shark = new Shark(point.x, point.y, size, now);
        board[point.x][point.y] = 0;
        
        return true;
    }
}