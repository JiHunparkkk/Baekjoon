class Solution {
        
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100_001;
        
        int left = 1;
        int right = 100_000;
        while(left <= right) {
            int nowLevel = (left + right) / 2;
            
            //해당 레벨일 때, limit안에 들어오는지?
            long totalTime = 0L;
            for(int i = 0; i < diffs.length; i++) {
                int nowDiff = diffs[i];
                int wasteTime = times[i];
                
                if(nowDiff <= nowLevel) {
                    totalTime += wasteTime;
                    continue;
                }
                totalTime += (wasteTime + times[i - 1]) * (nowDiff - nowLevel) + wasteTime;
            }
            if(totalTime > limit) {
                left = nowLevel + 1;
            } else {
                right = nowLevel - 1;
                // answer = Math.min(answer, nowLevel);
            }
        }
        // System.out.println(left);
        return left;
    }
}