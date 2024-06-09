import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static class Horse {
        int x, y, dir;

        public Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static int N, K;
    private static int[][] board;
    private static Horse[] horses;
    private static Stack<Integer>[][] stack;
    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        horses = new Horse[K + 1];
        stack = new Stack[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                stack[i][j] = new Stack<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y, dir;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(x, y, dir);
            stack[x][y].add(i);
        }

        System.out.println(run());
    }

    private static int run() {
        int gameCnt = 1;

        while (playRound()) {
            if(gameCnt > 1000) {
                return -1;
            }
            gameCnt++;
        }

        return gameCnt;
    }

    private static boolean playRound() {
        for (int i = 1; i <= K; i++) {
            int nx = horses[i].x + dx[horses[i].dir];
            int ny = horses[i].y + dy[horses[i].dir];

            if (!isIn(nx, ny) || board[nx][ny] == 2) {
                horses[i].dir = chaneDir(horses[i].dir);
                nx = horses[i].x + dx[horses[i].dir];
                ny = horses[i].y + dy[horses[i].dir];
            }

            int totalCnt = move(nx, ny, i);
            if(totalCnt >= 4) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 1 && ny >= 1 && nx <= N && ny <= N;
    }

    private static int chaneDir(int dir) {
        if (dir == 1 || dir == 3) {
            return dir + 1;
        }
        return dir - 1;
    }

    private static int move(int nx, int ny, int nowHorse) {
        Horse horse = horses[nowHorse];
        if (!isIn(nx, ny) || board[nx][ny] == 2) {
            return stack[horse.x][horse.y].size();
        }

        if (board[nx][ny] == 1) {
            while (!stack[horse.x][horse.y].isEmpty()) {
                int pop = stack[horse.x][horse.y].pop();
                stack[nx][ny].add(pop);
                horses[pop].x = nx;
                horses[pop].y = ny;
                if (pop == nowHorse) {
                   break;
                }
            }
        } else {
            Stack<Integer> tmp = new Stack<>();
            while(!stack[horse.x][horse.y].isEmpty()) {
                int pop = stack[horse.x][horse.y].pop();
                tmp.add(pop);
                horses[pop].x = nx;
                horses[pop].y = ny;
                if (pop == nowHorse) {
                    break;
                }
            }

            while (!tmp.isEmpty()) {
                stack[nx][ny].add(tmp.pop());
            }

        }
        return stack[nx][ny].size();
    }
}