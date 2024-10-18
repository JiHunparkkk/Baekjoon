import java.util.*;

class Solution {
    
    private static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static class Time{
        int now;
        
        public Time(int now) {
            this.now = now;
        }
        
        public void plus() {
            this.now++;
        }
    }

    private static List<Integer>[][] board;
    private static int answer;
    
    public int solution(int[][] points, int[][] routes) {        
        //로봇들을 각각 이동시킨다.
        //지나가는 경로에는 시간 값을 저장해놓는다.
        //만약 지나가는 길에 똑같은 시간이 있으면 위험상황 횟수를 +1 시킨다.
        board = new ArrayList[101][101];
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                board[i][j] = new ArrayList<Integer>();
            }
        }
        
        for(int robotNum = 0; robotNum < routes.length; robotNum++) {
            Time nowTime = new Time(0);

            for(int i = 0; i < routes[robotNum].length - 1; i++) {
                int startPoint = routes[robotNum][i] - 1;
                int endPoint = routes[robotNum][i + 1] - 1;
                Point start = new Point(points[startPoint][0], points[startPoint][1]);
                Point end = new Point(points[endPoint][0], points[endPoint][1]);

                if(nowTime.now == 0) {
                    addTime(start, nowTime);
                }
                moveRobot(start, end, nowTime);
            }
        }
        
        for(int i = 0; i < 101; i++) {
            for(int j = 0; j < 101; j++) {
                if(board[i][j].isEmpty()) {
                    continue;
                }
                Map<Integer, Integer> count = new HashMap<>();
                
                for(Integer num : board[i][j]) {
                    count.put(num, count.getOrDefault(num, 0) + 1);
                }
                
                for(Integer num : count.values()) {
                    if(num > 1) {
                        answer++;
                    }
                }
            }
        }
    
        return answer;
    }
    
    private void moveRobot(Point start, Point end, Time time) {
        
        while(start.x != end.x) {
            if(start.x > end.x) {
                start.x--;
            } else {
                start.x++;
            }
            time.plus();
            addTime(start, time);
        }
        
        while(start.y != end.y) {
            if(start.y > end.y) {
                start.y--;
            } else{
                start.y++;
            }
            time.plus();
            addTime(start, time);
        }
    }
    
    private void addTime(Point point, Time time) {
        // if(checkCrush(point, time)) {
        //     answer++;
        //     return;
        // }
        
        board[point.x][point.y].add(time.now);
    }
    
    private boolean checkCrush(Point point, Time time) {
        int x = point.x;
        int y = point.y;
        
        if(board[x][y].contains(time.now)) {
            return true;
        }
        return false;
    }
}