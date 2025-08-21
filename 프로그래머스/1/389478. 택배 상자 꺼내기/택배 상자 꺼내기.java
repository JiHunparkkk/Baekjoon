import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int[][] arr = new int[n][w];
        boolean flag = true;

        for(int i = 1, row = 0; i <= n;) {
            
            if(i > 1 && (i - 1) % w == 0) {
                flag = !flag;
                row++;
            }
            
            if(flag) {
                for(int j = 0; j < w && i <= n; j++) {
                    arr[row][j] = i++;
                }
            } else {
                for(int j = w - 1; j >= 0 && i <= n; j--) {
                    arr[row][j] = i++;
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < w; j++) {
                if(arr[i][j] == num) {
                    for(int k = i; k < n; k++) {
                        if(arr[k][j] != 0) {
                            answer++;
                        }
                    }
                    break;
                }
            }
        }
        
        return answer;
    }
}