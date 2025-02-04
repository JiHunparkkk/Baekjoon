import java.util.*;

class Solution {
    
    private static final int MAX_VALUE = 1000000;

    public int solution(int alp, int cop, int[][] problems) {
           
        int maxAlp, maxCop;
        
        maxAlp = maxCop = 0;
        for(int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for(int[] arr : dp) {
            Arrays.fill(arr, MAX_VALUE);
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        dp[alp][cop] = 0;
        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                if(i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                
                if(j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                for(int[] problem : problems) {
                    int nextAlp = Math.min(maxAlp, i + problem[2]);
                    int nextCop = Math.min(maxCop, j + problem[3]);
                    if(i >= problem[0] && j >= problem[1]) {
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}