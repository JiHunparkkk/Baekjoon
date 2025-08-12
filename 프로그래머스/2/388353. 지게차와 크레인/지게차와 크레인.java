import java.util.*;

class Solution {
    
    private static int n, m;
    private static char[][] storages;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        storages = new char[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                storages[i][j] = storage[i].charAt(j);
            }
        }
        
        int answer = 0;
        for(int i = 0; i < requests.length; i++) {
            boolean[][] visited = new boolean[n][m];
            
            if(requests[i].length() == 1) {
                answer += pullSide(requests[i].charAt(0), visited);
            } else {
                answer += pullAll(requests[i].charAt(0));
            }
        }
        
        return n * m - answer;
    }
    
    private static int pullSide(char target, boolean[][] visited) {
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(isSide(i ,j)) {
                    if(storages[i][j] == target) {
                        storages[i][j] = ' ';
                        count++;
                        visited[i][j] = true;
                    } else if(storages[i][j] == ' ' && !visited[i][j]) {
                        count += go(i, j, target, visited);
                    }
                }
            }
        }
        
        return count;
    }
    
    private static int go(int x, int y, char target, boolean[][] visited) {
        visited[x][y] = true;
        int result = 0;
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(isIn(nx, ny) && !visited[nx][ny]) {
                if(storages[nx][ny] == target) {
                    storages[nx][ny] = ' ';
                    result++;
                    visited[nx][ny] = true;
                } else if(storages[nx][ny] == ' ') {
                    result += go(nx, ny, target, visited);
                }
            }
        }
        
        return result;
    }
    
    private static boolean isSide(int i, int j) {
        return (i == 0 || j == 0 || i == n - 1 || j == m - 1);
    }
    
    private static boolean isBlank(int i, int j) {
        return storages[i][j] == ' ';
    }
    
    private static boolean isIn(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    
    private static int pullAll(char target) {
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(storages[i][j] == target) {
                    storages[i][j] = ' ';
                    count++;
                }
            }
        }
        
        return count;
    }
    
}