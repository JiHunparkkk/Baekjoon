import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static class Point implements Comparable<Point> {
        int x, y, w;
        int stairTime;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public void down() {
            stairTime++;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(w, o.w);
        }
    }

    private static int answer;
    private static PriorityQueue<Point> people1;
    private static PriorityQueue<Point> people2;
    private static List<Point> person;
    private static Point stair1;
    private static Point stair2;
    private static final boolean STAIR1 = true;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //입력
            int N = Integer.parseInt(br.readLine());
            int[][] board = new int[N][N];
            person = new ArrayList<>();
            stair1 = null;
            stair2 = null;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());

                    //사람 위치 저장
                    if (board[i][j] == 1) {
                        person.add(new Point(i, j, 0));
                    }
                    //계단 위치 저장
                    if (board[i][j] > 1) {
                        if (stair1 == null) {
                            stair1 = new Point(i, j, board[i][j]);
                        } else {
                            stair2 = new Point(i, j, board[i][j]);
                        }
                    }
                }
            }

            //사람들을 두 개의 팀으로 나누어 부분집합으로 계산
            answer = Integer.MAX_VALUE;
            chooseStair(new boolean[person.size()], 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void chooseStair(boolean[] choose, int depth) {
        if (depth == choose.length) {
            int time = move(choose);
            answer = Math.min(answer, time);
            return;
        }

        choose[depth] = true;
        chooseStair(choose, depth + 1);
        choose[depth] = false;
        chooseStair(choose, depth + 1);
    }

    private static int move(boolean[] choose) {
        //각 사람들이 배정된 계단으로 가는데 걸리는 시간을 오름차순으로 정렬
        checkDis(choose);

        //계단에 도착하면 큐에 담고 시간을 계산
        int time1, time2;
        time1 = downStair(stair1, people1);
        time2 = downStair(stair2, people2);

        return Math.max(time1, time2);
    }

    private static int downStair(Point stair, PriorityQueue<Point> people) {
        //계단에 도착하면 큐에 담고 시간을 계산한다.
        Queue<Point> queue = new ArrayDeque<>();
        int time = 0;

        while (!people.isEmpty() || !queue.isEmpty()) {
            //계단에 있는 사람들 이동
            time++;
            minorStairTime(stair, queue);

            //동시에 도착한 사람들 계단 길이만큼 출발
            while (!people.isEmpty() && time > people.peek().w
                    && queue.size() < 3) {
                queue.add(people.poll());
            }
        }

        return time;
    }

    private static void minorStairTime(Point stair, Queue<Point> queue) {
        for (Point p : queue) {
            p.down();

            if (p.stairTime >= stair.w) {
                queue.remove(p);
            }
        }
    }

    private static void checkDis(boolean[] choose) {
        people1 = new PriorityQueue<>();
        people2 = new PriorityQueue<>();

        //상태 리셋
        for (Point point : person) {
            point.stairTime = 0;
        }

        //거리 계산 후 저장
        for (int i = 0; i < person.size(); i++) {
            Point p = person.get(i);

            if (choose[i] == STAIR1) {
                int dis = Math.abs(p.x - stair1.x) + Math.abs(p.y - stair1.y);
                p.w = dis;
                people1.add(p);
            } else {
                int dis = Math.abs(p.x - stair2.x) + Math.abs(p.y - stair2.y);
                p.w = dis;
                people2.add(p);
            }
        }
    }
}