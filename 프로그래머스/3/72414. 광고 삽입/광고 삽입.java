class Solution {
    
    private static int[] times;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playLen = strToSec(play_time);
        int advLen = strToSec(adv_time);
        
        times = new int[360000];
        
        for(String log : logs) {
            int start = strToSec(log.split("-")[0]);
            int end = strToSec(log.split("-")[1]);
            for(int i = start; i < end; i++) {
                times[i]++;
            }
        }
        
        int index = 0;
        long sum = 0;
        long maxSum = 0;
        for(int i = 0; i < advLen; i++) {
            sum += times[i];
        }
        maxSum = sum;
        
        for(int i = advLen; i < playLen; i++) {
            sum += times[i] - times[i - advLen];
            if(maxSum < sum) {
                sum = maxSum;
                index = i - advLen + 1;
            }
        }
        
        return secToStr(index);
    }
    
    private static int strToSec(String time) {
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);
        int s = Integer.parseInt(time.split(":")[2]);
        
        return h * 3600 + m * 60 + s;
    }
    
    private static String secToStr(int time) {
        int h = time / 3600;
        int m = time % 3600 / 60;
        int s = time % 3600 % 60;
        
        String sh = h < 10 ? "0" + h : "" + h;
        String sm = m < 10 ? "0" + m : "" + m;
        String ss = s < 10 ? "0" + s : "" + s;
        return sh + ":" + sm + ":" + ss;
    }
}