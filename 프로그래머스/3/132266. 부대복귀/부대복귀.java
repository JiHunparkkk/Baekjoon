import java.util.*;

class Solution {
    
    //한 번 찾은 최단거리는 다시 찾지 않도록 한다.
    private static final int MAX_NUM = Integer.MAX_VALUE;
    private static List<List<Integer>> list = new ArrayList<>();
    private static Map<Integer, Integer> map = new HashMap<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        findDistance(n, destination);
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = map.getOrDefault(sources[i], -1);
        }
    
        return answer;
    }
    
    private static void findDistance(int n, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(destination);
        map.put(destination, 0);
        
        boolean[] visited = new boolean[n + 1];
        visited[destination] = true;
        
        int cnt = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int now = queue.poll();
                for(int j = 0; j < list.get(now).size(); j++) {
                    int next = list.get(now).get(j);
                                    
                    if(visited[next]) continue;
                    
                    map.put(next, cnt);            
                    visited[next] = true;
                    queue.add(next);
                }
            }
            cnt++;
        }
    }
}