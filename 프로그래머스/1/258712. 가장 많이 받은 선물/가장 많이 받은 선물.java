import java.util.*;

class Solution {
    
    private static Map<String, Map<String, Integer>> maps = new HashMap<>();
    private static Map<String, Integer> personalRecordGetMap = new HashMap<>();
    private static Map<String, Integer> personalRecordGiveMap = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        init(friends);
        recordGift(gifts);
        answer = findMaxGift();
        
        return answer;
    }
    
    private static void init(String[] friends) {
        for(String friend : friends) {
            maps.put(friend, new HashMap<>());
        }
    }
    
    private static void recordGift(String[] gifts) {
        for(String gift : gifts) {
            String A = gift.split(" ")[0];
            String B = gift.split(" ")[1];
            
            Map<String, Integer> m = maps.get(A);
            m.put(B, m.getOrDefault(B, 0) + 1);
            personalRecordGiveMap.put(A, personalRecordGiveMap.getOrDefault(A, 0) + 1);
            personalRecordGetMap.put(B, personalRecordGetMap.getOrDefault(B, 0) + 1);
        }
    }
    
    private static int findMaxGift() {
        Map<String, Integer> result = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        for(String pKey : maps.keySet()) {
            for(String sKey : maps.keySet()) {
                if(pKey.equals(sKey)) continue;

                int Aout = maps.get(pKey).getOrDefault(sKey, 0);
                int Bout = maps.get(sKey).getOrDefault(pKey, 0);
                
                String v1 = pKey + " " + sKey;
                String v2 = sKey + " " + pKey;
                if(visited.contains(v1) || visited.contains(v2)) continue;
                visited.add(v1);
                visited.add(v2);
                
                // System.out.println("START // " + pKey + ", " + sKey);
                
                if(Aout == Bout || (Aout == 0 && Bout == 0)) {
                    int APoint = personalRecordGiveMap.getOrDefault(pKey, 0) - personalRecordGetMap.getOrDefault(pKey, 0);
                    int BPoint = personalRecordGiveMap.getOrDefault(sKey, 0) - personalRecordGetMap.getOrDefault(sKey, 0);
                    
                    if(APoint > BPoint) {
                        result.put(pKey, result.getOrDefault(pKey, 0) + 1);
                        // System.out.println(pKey + " +1");
                    } else if(APoint < BPoint) {
                        result.put(sKey, result.getOrDefault(sKey, 0) + 1);
                        // System.out.println(sKey + " +1");
                    }
                } else {
                    if(Aout > Bout) {
                        result.put(pKey, result.getOrDefault(pKey, 0) + 1);
                        // System.out.println(pKey + " +1");
                    }
                    else {
                        result.put(sKey, result.getOrDefault(sKey, 0) + 1);
                        // System.out.println(sKey + " +1");
                    }
                }
            }    
        }
        
        int answer = 0;
        for(String key : result.keySet()) {
            int r = result.get(key);
            answer = Math.max(answer, r);
        }
        
        return answer;
    }
}