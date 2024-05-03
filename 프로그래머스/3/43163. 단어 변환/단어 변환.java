import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        int len = words.length;
        boolean[] visited = new boolean[len];
        Queue<String> queue = new ArrayDeque<>();
        queue.add(begin);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int j = 0; j < size; j++){
                String poll = queue.poll();
                
                if(poll.equals(target)) {
                    return answer;
                }
                
                for(int i = 0; i < len; i++) {
                    if(!visited[i] && canChange(poll, words[i])) {
                        visited[i] = true;
                        queue.add(words[i]);
                    }
                }
            }
            answer++;
        }
        
        return 0;
    }
    
    private static boolean canChange(String origin, String change) {
        int cnt = 0;
        for(int i = 0; i < origin.length(); i++) {
            if(origin.charAt(i) != change.charAt(i)) {
                cnt++;
            }
        }
        return cnt == 1;
    }
}