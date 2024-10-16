class Solution {
    
    private static class Time {
        int hour, minute;
        
        public Time(String hour, String minute) {
            this.hour = Integer.parseInt(hour);
            this.minute = Integer.parseInt(minute);
        }
        
        public boolean biggerThan(Time time) {
            if(this.hour == time.hour) {
                return this.minute >= time.minute;
            }
            return this.hour >= time.hour;
        }
        
        public void setTime(Time time) {
            this.hour = time.hour;
            this.minute = time.minute;
        }
        
        public void nextTime(Time limit) {
            minute += 10;
            if(minute >= 60) {
                hour++;
                minute -= 60;
            }
            
            if(biggerThan(limit)) {
                setTime(limit);
            }
        }
        
        public void prevTime() {
            minute -= 10;
            
            if(minute < 0) {
                hour--;
                minute += 60;
            }
            
            if(hour < 0 || minute < 0) {
                hour = 0;
                minute = 0;
            }
        }
    }
     
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        Time videoLen = new Time(video_len.split(":")[0], video_len.split(":")[1]);
        Time opStart = new Time(op_start.split(":")[0], op_start.split(":")[1]);
        Time opEnd = new Time(op_end.split(":")[0], op_end.split(":")[1]);
        Time nowPos = new Time(pos.split(":")[0], pos.split(":")[1]);
        
        for(String command : commands) {
            //현재 지점이 오프닝 시점인지 확인
            checkOpening(nowPos, opStart, opEnd);
            
            //명령어 실행
            playCommand(videoLen, nowPos, command);
        }
        checkOpening(nowPos, opStart, opEnd);
        
        String time = String.format("%02d", nowPos.hour);
        String minute = String.format("%02d", nowPos.minute);
        return time + ":" + minute;
    }
    
    private void checkOpening(Time nowPos, Time opStart, Time opEnd) {
        if(nowPos.biggerThan(opStart) && !nowPos.biggerThan(opEnd)) {
            nowPos.setTime(opEnd);
        }
    }
    
    private void playCommand(Time videoLen, Time nowPos, String command) {
        if("next".equals(command)) {
            nowPos.nextTime(videoLen);
        } 
        if("prev".equals(command)) {
            nowPos.prevTime();
        }
    }
}