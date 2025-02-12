class Solution {
        
    public long solution(int[] sequence) {
        long answer = Integer.MIN_VALUE;
        int length = sequence.length;
       
        long[][] dp = new long[length + 1][2];
        
        for(int i = 1; i <= length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], 0) + sequence[i - 1];
            dp[i][1] = Math.max(dp[i - 1][0], 0) - sequence[i - 1];
        }
        
        for(int i = 1; i <= length; i++) {
            long result = Math.max(dp[i][0], dp[i][1]);
            answer = Math.max(result, answer);
        }

        return answer;
    }
}