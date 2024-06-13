class Solution {
    
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n, m;
    private static int answer;
    private static boolean[][] r_visited;
    private static boolean[][] b_visited;
    private static int[][] map;
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = maze;
        
        r_visited = new boolean[n][m];
        b_visited = new boolean[n][m];
        Point red = null;
        Point blue = null;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 1) {
                    red = new Point(i, j);
                    r_visited[i][j] = true;
                }
                if(maze[i][j] == 2) {
                    blue = new Point(i, j);
                    b_visited[i][j] = true;
                }
            }
        }
        
        answer = Integer.MAX_VALUE;
        dfs(0, red, blue);
        
        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        
        return answer;
    }
    
    private static void dfs(int depth, Point red, Point blue) {
        boolean redGoal = false;
        boolean blueGoal = false;
        
        if(map[red.x][red.y] == 3) {
            redGoal = true;
        }
        if(map[blue.x][blue.y] == 4) {
            blueGoal = true;
        }
        
        if(redGoal && blueGoal) {
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int r_nx = red.x;
            int r_ny = red.y;
            if(!redGoal) {
                r_nx = red.x + dx[i];
                r_ny = red.y + dy[i];
            }
            
            if(canMove(r_nx, r_ny, r_visited) || redGoal) {
                for(int j = 0; j < 4; j++) {
                    int b_nx = blue.x;
                    int b_ny = blue.y;
                    
                    if(!blueGoal) {
                        b_nx = blue.x + dx[j];
                        b_ny = blue.y + dy[j];
                    }
                    
                    if(canMove(b_nx, b_ny, b_visited) || blueGoal) {
                        if(r_nx == b_nx && r_ny == b_ny) {
                            continue;
                        }
                        if(red.x == b_nx && red.y == b_ny && blue.x == r_nx && blue.y == r_ny) {
                            continue;
                        }
                        r_visited[r_nx][r_ny] = true;
                        b_visited[b_nx][b_ny] = true;
                        dfs(depth + 1, new Point(r_nx, r_ny), new Point(b_nx, b_ny));
                        r_visited[r_nx][r_ny] = false;
                        b_visited[b_nx][b_ny] = false;
                    }
                }
            }
        }
    }
    
    private static boolean canMove(int nx, int ny, boolean[][] visited) {
        if(nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return false;
        }
        
        if(map[nx][ny] == 5 || visited[nx][ny]) {
            return false;
        }
        
        return true;
    }
    
    private static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}