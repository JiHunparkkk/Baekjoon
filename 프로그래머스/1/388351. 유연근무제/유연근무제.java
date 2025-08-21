class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i = 0; i < schedules.length; i++) {
            int nowDay = startday;
            boolean flag = true;

            for(int j = 0; j < 7; j++) {
                if(nowDay == 6 || nowDay == 7)  {
                    nowDay++;
                    if(nowDay > 7) nowDay = 1;
                    continue;   
                }
   
                if(compareTimeForEvent(timelogs[i][j], schedules[i])) {
                    flag = false;
                    break;
                }
                nowDay++;
                if(nowDay > 7) nowDay = 1;
            }
            
            if(flag) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private static boolean compareTimeForEvent(int now, int limit) {
        int time = now / 100 - limit / 100;
        int min = now % 100 - limit % 100;
        if(min < 0) {
            min += 60;
            time--;
            
            time += min / 60;
            min %= 60;
        }
        
        int diff = time * 100 + min;
        return diff > 10;
    }
    
}