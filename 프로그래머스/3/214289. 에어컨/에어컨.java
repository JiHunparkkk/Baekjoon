import java.util.*;

class Solution {
    
    //i위치에 올 수 있는 값은
    //현재 온도를 j라 할 때, 전력량
    //dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1], dp[i - 1][j + 1])
    
    private static final int OFFSET = 10;
    private static int[][] dp;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        //인원 탑승 중 일때는 온도가 t1 ~ t2
        //그렇지 않을 때는 최소 온도, 최대온도
        int temp = temperature + OFFSET;
        
        dp = new int[onboard.length][51];
        for(int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], 10000000);
        }
        dp[0][temp] = 0;
        
        for(int i = 1; i < onboard.length; i++) {         
            int start = 0;
            int end = 50;
            
            if(onboard[i] == 1) {
                start = t1 + OFFSET;
                end = t2 + OFFSET;
            }
            
            for(int j = 0; j <= 50; j++) {
                if(onboard[i] == 1 && (t1  + OFFSET > j || t2 + OFFSET < j)) continue;

                //에어컨 on
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + b);
                if(j - 1 >= 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + a);
                if(j + 1 <= 50)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + a);
                
                //에어컨 Off
                if(j == temp) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                }
                if(j <= temp && j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
                if(j >= temp && j + 1 <= 50) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                }
            }
        }
        
        int answer = 10000000;
        for(int i = 0; i <= 50; i++) {
            if(onboard[onboard.length - 1] == 1 && (t1  + OFFSET > i || t2 + OFFSET < i)) continue;
            answer = Math.min(answer, dp[onboard.length - 1][i]);
        }
        
//         for(int i = 1; i < onboard.length; i++) {    
//             int result = 10000000;
//             for(int j = 0; j <= 50; j++) {
//                 result = Math.min(result, dp[i][j]);
//             }
//             System.out.println(result);
//         }
        
        return answer;
    }
    
}