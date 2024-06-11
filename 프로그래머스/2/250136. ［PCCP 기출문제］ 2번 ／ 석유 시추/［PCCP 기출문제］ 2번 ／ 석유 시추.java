import java.util.*;

class Solution {
    private static int n, m;
    private static int[][] groups;
    private static int[] cnt;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        groups = new int[n][m];
        cnt = new int[n * m / 2 + 1];
        
        int group = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(land[i][j] == 1) {
                   foundOil(group++, land, i, j);
                }
            }
        }
        
        return checkCntOil();
    }
    
    private static void foundOil(int group, int[][] land, int x, int y) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        groups[x][y] = group;
        land[x][y] = 0;
        cnt[group]++;
        
        while(!queue.isEmpty()) {
            Point poll = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                
                if(isIn(nx, ny) && land[nx][ny] == 1) {
                    groups[nx][ny] = group;
                    land[nx][ny] = 0;
                    cnt[group]++;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
    
    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }
    
    private static int checkCntOil() {
        int answer = 0;
        for(int i = 0; i < m; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < n; j++) {
                if(groups[j][i] > 0) {
                    set.add(groups[j][i]);
                }
            }
            
            int sum = 0;
            for(Integer group : set) {
                sum += cnt[group];
            }
            
            answer = Math.max(answer, sum);
        }
        return answer;
    }
 
    private static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}