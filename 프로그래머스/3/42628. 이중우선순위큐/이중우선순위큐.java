import java.util.*;

class Solution {
    
    private static Map<Integer, Integer> map = new HashMap<>();
    private static PriorityQueue<Integer> largeQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private static PriorityQueue<Integer> smallQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
    
    public int[] solution(String[] operations) {
        
        for(String op : operations) {
            int num = Integer.parseInt(op.split(" ")[1]);
            switch(op.charAt(0)) {
                case 'I' :
                    insert(num);
                    break;
                case 'D' :
                    delete(num);
                    break;
            }
        }
        
        if(largeQueue.isEmpty() || smallQueue.isEmpty()) {
            return new int[] {0, 0};
        }
        
        int large = largeQueue.poll();
        while(!largeQueue.isEmpty() && map.get(large) <= 0) {
            large = largeQueue.poll();
        }
        
        int small = smallQueue.poll();
        while(!smallQueue.isEmpty() && map.get(small) <= 0) {
            small = smallQueue.poll();
        }
        
        return new int[] {large, small};
    }
    
    private static void insert(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        largeQueue.add(num);
        smallQueue.add(num);
    }
    
    private static void delete(int num) {
        if(largeQueue.isEmpty() || smallQueue.isEmpty()) return;

        if(num == 1) {
            int poll = largeQueue.poll();
            while(!largeQueue.isEmpty() && map.get(poll) <= 0) {
                poll = largeQueue.poll();
            }
            map.put(poll, map.getOrDefault(poll, 0) - 1);
        } else {
            int poll = smallQueue.poll();
            while(!smallQueue.isEmpty() && map.get(poll) <= 0) {
                poll = smallQueue.poll();
            }
            map.put(poll, map.getOrDefault(poll, 0) - 1);
        }
    }
}