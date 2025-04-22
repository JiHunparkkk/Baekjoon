import java.util.*;

class Solution {
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        Map<Integer, Integer> outMap = new HashMap<>();
        Map<Integer, Integer> inMap = new HashMap<>();
        
        for(int i = 0; i < edges.length; i++) {
            outMap.put(edges[i][0], outMap.getOrDefault(edges[i][0], 0) + 1);
            inMap.put(edges[i][1], outMap.getOrDefault(edges[i][1], 0) + 1);
        }
        
        for(int key : outMap.keySet()) {
            if(outMap.get(key) >= 2)  {
                if(!inMap.containsKey(key)) {
                    answer[0] = key;
                } else {
                    answer[3]++;
                }
            }
        }
        
        for(int key : inMap.keySet()) {
            if(!outMap.containsKey(key)) {
                answer[2]++;
            }
        }
        
        answer[1] = outMap.get(answer[0]) - answer[2] - answer[3];
            
        return answer;
    }
    
}